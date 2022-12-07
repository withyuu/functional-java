package com.withyuu.functionaljava.concept;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PureFunctionTest {

    @Test
    void impureIsLessThanNumber() {
        int number = 10;
        Function<Integer, Boolean> isLessThanNumber = (input) -> input < number;
    }

    @Test
    void pureIsLessThanNumber() {
        BiFunction<Integer, Integer, Boolean> isLessThanNumber = (input, number) -> input < number;
    }

    @Test
    void impureSquareList() {
        Consumer<List<Integer>> squareList = (list) -> {
            for (int i = 0; i < list.size(); i++) {
                Integer value = list.get(i);
                list.set(i, value * value);
            }
        };
    }

    @Test
    void pureSquareList() {
        Function<List<Integer>, List<Integer>> squareList = (list) -> list.stream()
                .map(value -> value * value)
                .collect(Collectors.toList());
    }


}
