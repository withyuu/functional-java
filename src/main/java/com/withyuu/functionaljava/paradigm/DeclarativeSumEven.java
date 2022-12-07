package com.withyuu.functionaljava.paradigm;

import java.util.Arrays;

public class DeclarativeSumEven {

    public int sumEven(int[] numList) {
        return Arrays.stream(numList)
                .filter(this::isEven)
                .reduce(Integer::sum)
                .orElseGet(() -> 0);
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

}
