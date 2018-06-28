package net.spehl.jpa.techtalk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import net.spehl.jpa.techtalk.hibernate.JsonbType;
import net.spehl.jpa.techtalk.model.Person;

public class Generator {
    private static Random random = new Random();
    private static char[] letters = "qwertyuiopasdfghjklzxcvbnm".toCharArray();

    public static List<Person> createPerson(int numPerson, int numAttr, int numAtmTxn) {
        List<Person> persons = new ArrayList<>();
        return persons;
    }

    private static Person randomPerson() {
        return new Person(randomString(20), ((double)random.nextInt(100000))/100, UUID.randomUUID(), "{}");
    }

    private static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = letters[random.nextInt(letters.length)];
        }
        return new String(str);
    }

}
