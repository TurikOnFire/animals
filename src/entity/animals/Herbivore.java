package entity.animals;

import entity.Island;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxPerCell, int speed, double foodNeeded, int maxAge) {
        super(weight, maxPerCell, speed, foodNeeded, maxAge);
    }

    @Override
    public boolean eat(Island island) {
        if (satiety >= foodNeeded) return false;

        double plantsToEat = Math.min(foodNeeded - satiety, location.getPlants());
        if (plantsToEat > 0) {
            location.consumePlants(plantsToEat);
            satiety += plantsToEat;
            return true;
        }
        return false;
    }
}

