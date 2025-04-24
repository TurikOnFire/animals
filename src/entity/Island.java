package entity;

import entity.animals.Animal;
import entity.animals.herbivors.*;
import entity.animals.predators.*;
import simulation.Simulation;
import utils.Randomizer;
import utils.Settings;

public class Island {
    int height = Settings.ISLAND_HEIGHT;
    int width = Settings.ISLAND_WIDTH;
    Location[][] locations = new Location[height][width];
    public Simulation simulation;

    public Island(int height, int width) {
        this.height = height;
        this.width = width;
        this.locations = new Location[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                locations[y][x] = new Location(x, y);
            }
        }
    }

    public void startSimulation() {
        this.simulation = new Simulation(this);
        new Thread(simulation::start).start();
    }

    public void stopSimulation() {
        if (simulation != null) {
            simulation.stop();
        }
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return locations[y][x];
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void initialize() {
        for (int i = 0; i < 500; i++) {
            int x = Randomizer.nextInt(width);
            int y = Randomizer.nextInt(height);

            Animal animal = createRandomAnimal();
            animal.setLocation(locations[y][x]);
            locations[y][x].addAnimal(animal);
        }
    }

    private Animal createRandomAnimal() {
        int type = Randomizer.nextInt(15);

        return switch (type) {
            case 0 -> new Wolf();
            case 1 -> new Boar();
            case 2 -> new Fox();
            case 3 -> new Bear();
            case 4 -> new Eagle();
            case 5 -> new Horse();
            case 6 -> new Deer();
            case 7 -> new Rabbit();
            case 8 -> new Mouse();
            case 9 -> new Goat();
            case 10 -> new Sheep();
            case 11 -> new Snake();
            case 12 -> new Buffalo();
            case 13 -> new Duck();
            case 14 -> new Caterpillar();
            default -> new Rabbit();
        };
    }

    public Simulation getSimulation() {
        return simulation;
    }
}
