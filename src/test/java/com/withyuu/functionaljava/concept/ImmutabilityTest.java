package com.withyuu.functionaljava.concept;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutabilityTest {

    @Test
    void mutableList() {
        List<String> mutableList = new ArrayList<>();
        mutableList.add("a");
        mutableList.add("b");
        mutableList.add("c");
    }

    @Test
    void immutableList() {
        assertThrows(UnsupportedOperationException.class, () -> {
            List<String> immutableList = Arrays.asList("a", "b");
            immutableList.add("c");
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            List<String> immutableList = List.of("a", "b");
            immutableList.add("c");
        });
    }

}
