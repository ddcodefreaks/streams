package com.example.product.app.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTesting {

    public static void streamOperation() {
         /*get stream of val1,val2,val3*/
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8);
        stream.forEach(integer -> System.out.print("stream::" + integer));
        System.out.println("\n");
        /*Using Stream.of(arrayOfElements)*/
        Stream<Integer> stream1 = Stream.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        stream1.forEach(integer -> System.out.print("stream1::" + integer));
        System.out.println("\n");

  /*Using someList.stream()*/
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        /*convert list in to stream*/
        Stream<Integer> stream3 = list.stream();
        stream3.forEach(integer -> System.out.print("Stream2:::" + integer));
        System.out.println("\n");
    }

    /*this methos converts array of string in collection and filters the empty string and printsout the result */
    public static void filterEmptyString() {
        /*creating a collection from array */
        List<String> str = Arrays.asList("abc", "bbc", "", "", "ffg", "ggh", "hhj", "yyy", "fff");
      /*write down the code which filters out the empty string and get that in collection */
        List<String> result = str.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
      /*printing the results*/
        for (String str1 : result) {
            System.out.println(str1);
        }
    }

    /*this method prints the random number and limits the number and prints with the same stream*/
    public static void printRandom() {
            /*code to print 10 random numbers randomly with foreach()*/
        Random random = new Random();
        random.longs().limit(10).forEach(System.out::println);
    }

    /**
     * The following code segment prints unique squares of numbers using map.
     */
    public static void getUniqueSquare() {
        /*get some array of integers */
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        /*get list of unique squares*/
        

    }

    public static void main(String... args) {
        System.out.println("-------project Started----------");
        // streamOperation();
        //filterEmptyString();
        //printRandom();
    }


}
