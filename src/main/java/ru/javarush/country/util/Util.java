package ru.javarush.country.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Util() {
    }

    public static List<Integer> getRandomIntList(int interval, int light) {
        Set<Integer> intSet = new HashSet<>();
        for (int i = 0; i < light; i++) {
            if (!intSet.add(ThreadLocalRandom.current().nextInt(1, interval))) {
                light++;
            }
        }
        return new ArrayList<>(intSet);
    }

    public static String randomString(int lenght, boolean upCase) {
        StringBuilder randomString = new StringBuilder("" + alphabet.charAt(ThreadLocalRandom.current()
                .nextInt(0, 26)));

        if (upCase) {
            randomString = new StringBuilder(randomString.toString().toUpperCase());
            lenght--;
        }

        for (int i = 0; i <= lenght; i++) {
            randomString.append(alphabet.charAt(ThreadLocalRandom.current().nextInt(0, 26)));
        }
        return randomString.toString();
    }

     public static String randomCity() {
        return "" + randomString(ThreadLocalRandom.current().nextInt(4, 9), true);
    }


}
