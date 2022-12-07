package com.withyuu.functionaljava.concept;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HigherOrderFunctionTest {

    @Test
    void canAssignFunctionToVariable() {
        Function<Integer, Integer> plusTen = i -> i + 10;
        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b;
    }

    @Test
    void canReceiveFunctionAsParameter() {
        String[] spells = {"Lumos", "Crucio", "Reducio", "Expelliarmus"};
        Arrays.sort(spells, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
    }

    @Test
    void canReturnFunction() {
        Function<Integer, Boolean> isMoreThanTen = getCompareFunction(10);
        assertFalse(isMoreThanTen.apply(5));
        assertTrue(isMoreThanTen.apply(11));

        Function<Integer, Boolean> isMoreThanTwenty = getCompareFunction(20);
        assertFalse(isMoreThanTwenty.apply(5));
        assertFalse(isMoreThanTwenty.apply(11));
        assertTrue(isMoreThanTwenty.apply(27));
    }

    private Function<Integer, Boolean> getCompareFunction(int value) {
        return (toCompareWith) -> toCompareWith > value;
    }

}
