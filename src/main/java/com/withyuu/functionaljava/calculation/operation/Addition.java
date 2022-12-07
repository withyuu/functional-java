package com.withyuu.functionaljava.calculation.operation;

public class Addition implements Operation {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
