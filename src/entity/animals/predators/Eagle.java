package entity.animals.predators;

import entity.Island;
import entity.animals.Animal;
import entity.animals.Predator;
import entity.animals.herbivors.*;

public class Eagle extends Predator {
    public Eagle() {
        super(6, 20, 3, 1, 25);
    }

    @Override
    public boolean eat(Island island) {
        if (satiety >= foodNeeded) return false;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Fox) {
                if (tryToEat(animal, 10)) return true;
            } else if (animal instanceof Rabbit) {
                if (tryToEat(animal, 90)) return true;
            } else if (animal instanceof Mouse) {
                if (tryToEat(animal, 90)) return true;
            } else if (animal instanceof Duck) {
                if (tryToEat(animal, 80)) return true;
            }
        }
        return false;
    }
}
