package simulation;

import entity.Island;
import entity.Location;
import entity.animals.Animal;
import statistic.DeathCause;
import statistic.Statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Simulation {
    private final Island island;
    private final ExecutorService executor;
    private volatile boolean isRunning;
    private final Lock lock = new ReentrantLock();
    private final Statistics statistics = new Statistics();
    private int currentDay = 0;


    public Statistics getStatistics() {
        return statistics;
    }


    public Simulation(Island island) {
        this.island = island;
        int processors = Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(processors);
        System.out.println("Simulation started with " + processors + " threads");
    }

    public void start() {
        isRunning = true;
        currentDay = 0;

        while (isRunning) {
            currentDay++;
            System.out.println("\n=== Starting Day " + currentDay + " ===");

            try {
                // Выполняем один шаг симуляции
                runSimulationStep(currentDay);

                // Пауза между шагами симуляции
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                stop();
            }
        }
    }

    private void runSimulationStep(int day) {
        List<Future<?>> futures = new ArrayList<>();

        // Обрабатываем локации и животных в локации
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location location = island.getLocation(x, y);

                // Обработка растений в локации
                location.growPlants();

                // Обработка каждого животного в отдельном потоке
                for (Animal animal : location.getAnimals()) {
                    futures.add(executor.submit(() -> processAnimal(animal)));
                }
            }
        }

        // Ожидаем завершения всех задач
        waitForCompletion(futures);

        // Удаляем умерших животных
        cleanDeadAnimals();

        // Печатаем статистику за день
        statistics.printDailyStatistics(day);

        // Сбрасываем ежедневную статистику
        statistics.resetDailyStatistics();
    }

    private void processAnimal(Animal animal) {
        try {
            lock.lock();
            if (!animal.isAlive()) return;

            // Сохраняем предыдущее состояние сытости
            double previousSatiety = animal.getSatiety();

            animal.move(island);
            animal.eat(island);
            animal.reproduce(island);
            animal.age(island);

            // Проверяем, умерло ли животное
//            if (!animal.isAlive()) {
//                DeathCause cause = determineDeathCause(animal, previousSatiety);
//                statistics.registerAnimalDeath(animal.getClass(), cause);
//            }
        } finally {
            lock.unlock();
        }
    }

//    private DeathCause determineDeathCause(Animal animal, double previousSatiety) {
//        if (animal.getAge() >= animal.getMaxAge()) {
//            return DeathCause.OLD_AGE;
//        }
//        return previousSatiety <= 0 ? DeathCause.STARVATION : DeathCause.EATEN;
//    }

    private void waitForCompletion(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                System.err.println("Error in simulation thread: " + e.getMessage());
            }
        }
    }

    private void cleanDeadAnimals() {
        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                island.getLocation(x, y).removeDeadAnimals();
            }
        }
    }

    public void stop() {
        isRunning = false;
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Симуляция остановлена");
    }

}
