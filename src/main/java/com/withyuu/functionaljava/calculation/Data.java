package com.withyuu.functionaljava.calculation;

import com.withyuu.functionaljava.calculation.operation.Operation;

public class Data {

    private int a;
    private int b;

    public Data(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int apply(Operation operation) {
        return operation.calculate(a, b);
    }

}
