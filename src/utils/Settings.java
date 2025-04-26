package utils;

public class Settings {

    private int ISLAND_HEIGHT;
    private int ISLAND_WIDTH;
    private Integer timeForIslandLife;
    private int startAmountOfAnimals;
    private boolean allAnimalsDead;
    private boolean allHerbivoresDead;

    public Settings(int ISLAND_HEIGHT, int ISLAND_WIDTH, Integer timeForIslandLife, int startAmountOfAnimals) {
        this.ISLAND_HEIGHT = ISLAND_HEIGHT;
        this.ISLAND_WIDTH = ISLAND_WIDTH;
        this.timeForIslandLife = timeForIslandLife;
        this.startAmountOfAnimals = startAmountOfAnimals;
    }

    public int getISLAND_HEIGHT() {
        return ISLAND_HEIGHT;
    }

    public int getISLAND_WIDTH() {
        return ISLAND_WIDTH;
    }

    public Integer getTimeForIslandLife() {
        return timeForIslandLife;
    }

    public int getStartAmountOfAnimals() {
        return startAmountOfAnimals;
    }

    public boolean isAllAnimalsDead() {
        return allAnimalsDead;
    }

    public boolean isAllHerbivoresDead() {
        return allHerbivoresDead;
    }
}
