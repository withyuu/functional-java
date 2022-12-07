package com.withyuu.functionaljava.paradigm;

public class ImperativeSumEven {

    public int sumEven(int[] numList) {
        int result = 0;
        for (int num : numList) {
            if (num % 2 == 0) {
                result += num;
            }
        }
        return result;
    }

}
