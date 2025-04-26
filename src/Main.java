import entity.Island;
import utils.Settings;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Height:");
        int height = scanner.nextInt();
        System.out.println("Width:");
        int width = scanner.nextInt();
        System.out.println("Count of animals at start:");
        int startAnimals = scanner.nextInt();
        System.out.println("Time for life on Island");
        Integer timeForIsland = scanner.nextInt();
        Settings settings = new Settings(height, width, timeForIsland, startAnimals);
        Island island = new Island(settings);
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

