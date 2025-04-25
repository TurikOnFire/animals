import entity.Island;
import utils.Settings;

import java.util.Set;

public class Main {
    public static void main(String[] args) {


        Island island = new Island(2, 2);
        island.initialize();
        island.startSimulation();
//        try {
//            Thread.sleep(20000);
//            island.stopSimulation();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }
}

