package entity.animals.predators;

import entity.Island;
import entity.animals.Animal;
import entity.animals.Predator;
import entity.animals.herbivors.*;

public class Snake extends Predator {
    public Snake() {
        super(15, 30, 1, 3, 20);
    }

    @Override
    public boolean eat(Island island) {
        if (satiety >= foodNeeded) return false;

        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Rabbit) {
                if (tryToEat(animal, 20, island)) return true;
            } else if (animal instanceof Fox) {
                if (tryToEat(animal, 15, island)) return true;
            } else if (animal instanceof Mouse) {
                if (tryToEat(animal, 40, island)) return true;
            } else if (animal instanceof Duck) {
                if (tryToEat(animal, 10, island)) return true;
            }
        }
        return false;
    }
}
