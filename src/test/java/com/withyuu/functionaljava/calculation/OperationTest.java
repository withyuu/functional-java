package com.withyuu.functionaljava.calculation;

import com.withyuu.functionaljava.calculation.operation.Addition;
import com.withyuu.functionaljava.calculation.operation.Operation;
import com.withyuu.functionaljava.calculation.operation.Subtraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {

    @Test
    void shouldApplyAddition() {
        Data data = new Data(7, 11);
        int actual = data.apply(new Addition());
        assertEquals(18, actual);
    }

    @Test
    void shouldApplySubtraction() {
        Data data = new Data(7, 11);
        int actual = data.apply(new Subtraction());
        assertEquals(-4, actual);
    }

    @Test
    void shouldApplyMultiplicationWithAnonymousClass() {
        Data data = new Data(12, 3);
        int actual = data.apply(new Operation() {
            @Override
            public int calculate(int a, int b) {
                return a * b;
            }
        });
        assertEquals(36, actual);
    }

    @Test
    void shouldApplyDivisionWithLambda() {
        Data data = new Data(12, 3);
        int actual = data.apply((a, b) -> a / b);
        assertEquals(4, actual);
    }

    @Test
    void shouldApplyPowerWithMethodReference() {
        Data data = new Data(3, 4);
        int actual = data.apply(this::power);
        assertEquals(81, actual);
    }

    public int power(int a, int b) {
        return (int) Math.pow(a, b);
    }

}
