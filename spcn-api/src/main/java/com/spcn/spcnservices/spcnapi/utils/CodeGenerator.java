package com.spcn.spcnservices.spcnapi.utils;


import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CodeGenerator {

    public static String getRandomCode(){

        // seed for randomness - for permutation of list (not the integers)
        Random rnd = new Random(System.currentTimeMillis());

        // generate randomized sequence as a List
        List<Integer> numbers;
        Collections.shuffle((numbers = IntStream.rangeClosed(1000, 9999).boxed().collect(Collectors.toList())), rnd);

        int randomElement = (int)(Math.random()*9000)+1000;

        return String.valueOf(numbers.get(randomElement));
    }

}
