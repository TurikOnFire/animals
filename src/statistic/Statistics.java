package statistic;

import entity.animals.Animal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {
    private final Map<Class<? extends Animal>, AtomicInteger> currentPopulation = new ConcurrentHashMap<>();
    private final Map<Class<? extends Animal>, AtomicInteger> animalsEaten = new ConcurrentHashMap<>();
    private final Map<Class<? extends Animal>, AtomicInteger> starvedToDeath = new ConcurrentHashMap<>();
    private final Map<Class<? extends Animal>, AtomicInteger> diedOfOldAge = new ConcurrentHashMap<>();
    private final Map<Class<? extends Animal>, AtomicInteger> animalsBorn = new ConcurrentHashMap<>();

    public void registerNewAnimal(Class<? extends Animal> animalClass) {
        currentPopulation.computeIfAbsent(animalClass, k -> new AtomicInteger()).incrementAndGet();
    }

    public void registerAnimalBirth(Class<? extends Animal> animalClass) {
        animalsBorn.computeIfAbsent(animalClass, k -> new AtomicInteger()).incrementAndGet();
        registerNewAnimal(animalClass);
    }

    public void registerAnimalDeath(Class<? extends Animal> animalClass, DeathCause cause) {
        // Сначала проверяем, есть ли такие животные в популяции
        AtomicInteger count = currentPopulation.get(animalClass);
        if (count != null && count.get() > 0) {
            count.decrementAndGet();

            // Затем регистрируем причину смерти
            switch (cause) {
                case EATEN -> animalsEaten.computeIfAbsent(animalClass, k -> new AtomicInteger()).incrementAndGet();
                case STARVATION -> starvedToDeath.computeIfAbsent(animalClass, k -> new AtomicInteger()).incrementAndGet();
                case OLD_AGE -> diedOfOldAge.computeIfAbsent(animalClass, k -> new AtomicInteger()).incrementAndGet();
            }
        }
    }

    public int getTotalPopulation() {
        return currentPopulation.values().stream().mapToInt(AtomicInteger::get).sum();
    }

    public void printDailyStatistics(int day) {
        System.out.printf("\n=== Day %d Statistics ===%n", day);
        printStatistic("Current Population", currentPopulation);
        printStatistic("Animals Born", animalsBorn);
        printStatistic("Animals Eaten", animalsEaten);
        printStatistic("Animals Starved", starvedToDeath);
        printStatistic("Animals Died of Old Age", diedOfOldAge);
        System.out.printf("Total population: %d%n", getTotalPopulation());
    }

    // Сброс ежедневной статистики (кроме текущей популяции)
    public void resetDailyStatistics() {
        animalsEaten.clear();
        starvedToDeath.clear();
        diedOfOldAge.clear();
        animalsBorn.clear();
    }

    private void printStatistic(String title, Map<Class<? extends Animal>, AtomicInteger> data) {
        System.out.println("\n" + title + ":");
        if (data.isEmpty()) {
            System.out.println("  No data");
            return;
        }

        data.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().getSimpleName()))
                .forEach(entry -> {
                    if (entry.getValue().get() > 0) {
                        System.out.printf("  %-20s: %d%n",
                                entry.getKey().getSimpleName(),
                                entry.getValue().get());
                    }
                });
    }
}