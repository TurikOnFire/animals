package entity.animals.predators;

import entity.Island;
import entity.animals.Animal;
import entity.animals.Predator;
import entity.animals.herbivors.*;

public class Fox extends Predator {
    public Fox() {
        super(8, 30, 2, 2, 7);
    }

    @Override
    public boolean eat(Island island) {
        if (satiety >= foodNeeded) return false;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Rabbit) {
                if (tryToEat(animal, 70, island)) return true;
            } else if (animal instanceof Mouse) {
                if (tryToEat(animal, 90, island)) return true;
            } else if (animal instanceof Duck) {
                if (tryToEat(animal, 60, island)) return true;
            } else if (animal instanceof Caterpillar) {
                if (tryToEat(animal, 40, island)) return true;
            }
        }
        return false;
    }
}
