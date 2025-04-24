package entity.animals;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxPerCell, int speed, double foodNeeded, int maxAge) {
        super(weight, maxPerCell, speed, foodNeeded, maxAge);
    }
}
