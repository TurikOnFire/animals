package entity.animals;

import entity.Island;
import entity.Location;
import statistic.DeathCause;
import utils.Randomizer;

import java.util.List;

public abstract class Animal {
    protected double weight;
    protected int maxPerCell;
    protected int speed;
    protected double foodNeeded;
    protected double satiety;
    protected boolean alive = true;
    protected int age;
    protected int maxAge;
    protected Location location;

    public Animal(double weight, int maxPerCell, int speed, double foodNeeded, int maxAge) {
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.maxAge = maxAge;
        this.satiety = foodNeeded * 0.5;
    }

    public abstract boolean eat(Island island);

    public void move(Island island) {
        if (!alive || speed == 0) return;

        for (int i = 0; i < speed; i++) {
            if (Randomizer.nextDouble() > 0.8) continue; // 20% вероятность не двигаться

            int direction = Randomizer.nextInt(4);
            int newX = location.getX();
            int newY = location.getY();

            switch (direction) {
                case 0 -> newX++; // вправо
                case 1 -> newX--; // влево
                case 2 -> newY++; // вниз
                case 3 -> newY--; // вверх
            }

            Location newLocation = island.getLocation(newX, newY);
            if (newLocation != null && canMoveTo(newLocation)) {
                location.removeAnimal(this);
                this.location = newLocation;
                newLocation.addAnimal(this);
            }
        }
    }

    protected boolean canMoveTo(Location newLocation) {
        long sameTypeCount = newLocation.getAnimals().stream()
                .filter(a -> a.getClass() == this.getClass())
                .count();
        return sameTypeCount < maxPerCell;
    }

    public void reproduce(Island island) {
        if (!alive || satiety < foodNeeded * 0.5) return;

        List<Animal> partners = location.getAnimals().stream()
                .filter(a -> a.getClass() == this.getClass())
                .filter(a -> a != this)
                .filter(a -> a.satiety >= a.foodNeeded * 0.5)
                .toList();

        if (!partners.isEmpty() && Randomizer.checkProbability(0.5)) {
            try {
                Animal offspring = this.getClass().getDeclaredConstructor().newInstance();
                offspring.location = location;
                location.addAnimal(offspring);
                island.getSimulation().getStatistics().registerAnimalBirth(this.getClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void age(Island island) {
        age++;
        satiety -= foodNeeded * 0.1;
        if (age >= maxAge) {
            island.getSimulation().getStatistics().registerAnimalDeath(this.getClass(), DeathCause.OLD_AGE);
            alive = false;
        } else if (satiety <= 0) {
            island.getSimulation().getStatistics().registerAnimalDeath(this.getClass(), DeathCause.STARVATION);
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getWeight() {
        return weight;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    protected boolean tryToEat(Animal prey, int probability, Island island) {
        if (Randomizer.nextInt(100) < probability) {
            satiety = Math.min(satiety + prey.getWeight(), foodNeeded);
            prey.alive = false;
            island.getSimulation().getStatistics().registerAnimalDeath(prey.getClass(), DeathCause.EATEN);
            return true;
        }
        return false;
    }

    public double getSatiety() {
        return satiety;
    }

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

}
