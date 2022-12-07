package com.withyuu.functionaljava.paradigm;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImperativeSumEvenTest {

    @Test
    void shouldReturnZeroWhenArrayIsEmpty() {
        int[] emptyArray = new int[0];
        ImperativeSumEven sut = new ImperativeSumEven();
        int actual = sut.sumEven(emptyArray);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void shouldReturnZeroWhenArrayContainsOnlyOddNumber() {
        int[] oddNumberArray = {1, 3, 5, 7, 9};
        ImperativeSumEven sut = new ImperativeSumEven();
        int actual = sut.sumEven(oddNumberArray);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void shouldReturnSumOfEvenNumbersInArray() {
        int[] oddNumberArray = {0, 1, 3, 5, 7, 8, 9, 4};
        ImperativeSumEven sut = new ImperativeSumEven();
        int actual = sut.sumEven(oddNumberArray);
        Assertions.assertEquals(12, actual);
    }


}
