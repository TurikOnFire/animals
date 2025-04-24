import entity.Island;

public class Main {
    public static void main(String[] args) {

        Island island = new Island(10, 10);
        island.initialize();
        island.startSimulation();
        try {
            Thread.sleep(20000);
            island.stopSimulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

