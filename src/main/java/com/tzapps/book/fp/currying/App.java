package com.tzapps.book.fp.currying;

import clojure.lang.IPersistentCollection;
import clojure.lang.IPersistentVector;
import clojure.lang.PersistentVector;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class App {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Pudro", "Bevilacqua"),
                new Person("Make", "Vasquez"),
                new Person("Aba", "Aarons")
        );

        //classic Java
        Collections.sort(people, new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return p1.getFirstname().compareTo(p2.getFirstname());
            }
        });

        for (Person person : people) {
            System.out.println(person);
        }

        //Java 8
        //v1 - anonymous function
        Collections.sort(people, (pers1, pers2) -> pers1.getFirstname().compareTo(pers2.getFirstname()));
        people.forEach(System.out::println);

        //v2 - Java library
        Collections.sort(people, Comparator.comparing(Person::getFirstname));

        //v3 - Currying   for fun
        MyComparator<Person> comparator = keyExtractor -> (pers1, pers2) -> keyExtractor.apply(pers1).compareTo(keyExtractor.apply(pers2));
        Collections.sort(people, (p1, p2) -> comparator.apply(Person::getFirstname).apply(p1, p2));

        Comparing<Person> sortBySecondChar = comparator.apply(person -> person.getFirstname().substring(1));
        Collections.sort(people, (p1, p2) -> sortBySecondChar.apply(p1, p2));

        people.forEach(System.out::println);

        Supplier<UUID> randomUUIDSupplier = UUID::randomUUID;
        Supplier<Stream<UUID>> keyGenerator = () -> Stream.generate(randomUUIDSupplier);
        IPersistentVector nextTenKeys = PersistentVector.create(keyGenerator.get().limit(10).collect(Collectors.toList()));
        IPersistentCollection nextFiveKeys = PersistentVector.create(keyGenerator.get().limit(5).collect(Collectors.toList()));

        System.out.println(nextTenKeys);
        System.out.println(nextFiveKeys);

    }

    @java.lang.FunctionalInterface
    interface MyComparator<T> {
        Comparing<T> apply(Function<T, String> keyExtractor);
    }

    @java.lang.FunctionalInterface
    interface Comparing<T> {
        int apply(T o1, T o2);
    }
}

class Person {
    final String firstname;
    final String lastname;

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
