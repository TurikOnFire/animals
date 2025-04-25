package entity.animals.herbivors;

import entity.Island;
import entity.animals.Animal;
import entity.animals.Herbivore;

public class Boar extends Herbivore {
    public Boar() {
        super(400, 50, 2, 50, 11);
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
            } else if (animal instanceof Mouse) {
                if (tryToEat(animal, 50, island)) return true;
            }
        }
        return false;
    }
}
