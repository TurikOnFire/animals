package entity.animals.predators;

import entity.Island;
import entity.animals.Animal;
import entity.animals.Predator;
import entity.animals.herbivors.*;

public class Wolf extends Predator {
    public Wolf() {
        super(50, 30, 3, 8, 15);
    }

    @Override
    public boolean eat(Island island) {
        if (satiety >= foodNeeded) return false;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Rabbit) {
                if (tryToEat(animal, 60, island)) return true;
            } else if (animal instanceof Horse) {
                if (tryToEat(animal, 10, island)) return true;
            } else if (animal instanceof Deer) {
                if (tryToEat(animal, 15, island)) return true;
            } else if (animal instanceof Mouse) {
                if (tryToEat(animal, 80, island)) return true;
            } else if (animal instanceof Goat) {
                if (tryToEat(animal, 60, island)) return true;
            } else if (animal instanceof Sheep) {
                if (tryToEat(animal, 70, island)) return true;
            } else if (animal instanceof Boar) {
                if (tryToEat(animal, 15, island)) return true;
            } else if (animal instanceof Buffalo) {
                if (tryToEat(animal, 10, island)) return true;
            } else if (animal instanceof Duck) {
                if (tryToEat(animal, 40, island)) return true;
            }
        }
        return false;
    }
}
