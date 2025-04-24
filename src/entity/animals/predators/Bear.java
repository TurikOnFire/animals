package entity.animals.predators;

import entity.Island;
import entity.animals.Animal;
import entity.animals.Predator;
import entity.animals.herbivors.*;

public class Bear extends Predator {
    public Bear() {
        super(500, 5, 2, 80, 25);
    }

    @Override
    public boolean eat(Island island) {
        if (satiety >= foodNeeded) return false;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Snake) {
                if (tryToEat(animal, 80)) return true;
            } else if (animal instanceof Horse) {
                if (tryToEat(animal, 40)) return true;
            } else if (animal instanceof Deer) {
                if (tryToEat(animal, 80)) return true;
            } else if (animal instanceof Rabbit) {
                if (tryToEat(animal, 80)) return true;
            } else if (animal instanceof Mouse) {
                if (tryToEat(animal, 90)) return true;
            } else if (animal instanceof Goat) {
                if (tryToEat(animal, 70)) return true;
            } else if (animal instanceof Sheep) {
                if (tryToEat(animal, 70)) return true;
            } else if (animal instanceof Boar) {
                if (tryToEat(animal, 50)) return true;
            } else if (animal instanceof Buffalo) {
                if (tryToEat(animal, 20)) return true;
            } else if (animal instanceof Duck) {
                if (tryToEat(animal, 10)) return true;
            }
        }
        return false;
    }
}
