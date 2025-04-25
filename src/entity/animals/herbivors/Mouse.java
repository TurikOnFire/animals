package entity.animals.herbivors;

import entity.Island;
import entity.animals.Animal;
import entity.animals.Herbivore;

public class Mouse extends Herbivore {
    public Mouse() {
        super(0.05, 500, 1, 0.01, 2);
    }

    @Override
    public boolean eat(Island island) {
        if (satiety >= foodNeeded)
            return false;

        double plantsToEat = Math.min(foodNeeded - satiety, location.getPlants());
        if (plantsToEat > 0) {
            location.consumePlants(plantsToEat);
            satiety += plantsToEat;
            return true;
        }
        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Caterpillar) {
                if (tryToEat(animal, 90, island)) return true;
            }
        }
        return false;
    }
}
