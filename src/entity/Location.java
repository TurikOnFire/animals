package entity;

import entity.animals.Animal;
import utils.Randomizer;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {

    private final int x;
    private final int y;
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private double plants;
    private final double maxPlants;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.maxPlants = 200;
        this.plants = maxPlants * Randomizer.nextDouble();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void removeDeadAnimals() {
        animals.removeIf(animal -> !animal.isAlive());
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    public double getPlants() {
        return plants;
    }

    public void consumePlants(double amount) {
        plants = Math.max(0, plants - amount);
    }

    public void growPlants() {
        if (plants < maxPlants) {
            plants += maxPlants * 0.1 * Randomizer.nextDouble();
            plants = Math.min(plants, maxPlants);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
