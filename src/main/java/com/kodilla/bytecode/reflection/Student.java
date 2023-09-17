package com.kodilla.bytecode.reflection;

import java.util.Random;

public class Student {
    private final String indexNumber;

    public Student(int z) {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();

        this.indexNumber = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(z)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}