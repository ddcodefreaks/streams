package com.example.product.app.methodreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * created from: https://www.tutorialspoint.com/java8/java8_lambda_expressions.htm
 * this method tests the method reference for the new java 8 stream api's
 */
public class TestMethodReference {

    public static void testHighest() {
        List<Integer> integres = Arrays.asList(1, 12, 42, 52);
        Optional<Integer> max = integres.stream().reduce(Math::max);
        max.ifPresent(System.out::println);
    }

    public static void testCompareTo() {
        List<String> str = Arrays.asList("love", "to", "code", "in", "java");
        List<String> sorted = str.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
        System.out.println("Sorted data::" + sorted);
        /*using the same by method refernec*/
        List<String> data = str.stream().sorted(String::compareTo).collect(Collectors.toList());
        System.out.println("data::" + data);
    }

    public static void listIntegers() {
        List<Integer> integers = IntStream.range(1, 100).boxed().collect(Collectors.toCollection(ArrayList::new));
        Optional<Integer> max = integers.stream().reduce(Math::max);
        max.ifPresent(System.out::println);
    }


    public static void main(String... args) {
        //TestMethodReference.testHighest();
        //TestMethodReference.testCompareTo();
        TestMethodReference.listIntegers();


    }

}
