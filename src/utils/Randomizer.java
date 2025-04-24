package utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    private Randomizer() {}

    public static int nextInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int nextInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public static int nextInt(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    public static long nextLong() {
        return ThreadLocalRandom.current().nextLong();
    }

    public static long nextLong(long bound) {
        return ThreadLocalRandom.current().nextLong(bound);
    }

    public static long nextLong(long origin, long bound) {
        return ThreadLocalRandom.current().nextLong(origin, bound);
    }

    public static double nextDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static double nextDouble(double bound) {
        return ThreadLocalRandom.current().nextDouble(bound);
    }

    public static double nextDouble(double origin, double bound) {
        return ThreadLocalRandom.current().nextDouble(origin, bound);
    }

    public static boolean nextBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static float nextFloat() {
        return ThreadLocalRandom.current().nextFloat();
    }

    // Метод для вероятностных проверок
    public static boolean checkProbability(double probability) {
        if (probability <= 0) return false;
        if (probability >= 1) return true;
        return ThreadLocalRandom.current().nextDouble() < probability;
    }

    // Метод для получения случайного элемента из списка
    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List не может быть пустым");
        }
        return list.get(nextInt(list.size()));
    }

    // Метод для получения случайного элемента из массива
    public static <T> T getRandomElement(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }
        return array[nextInt(array.length)];
    }
}