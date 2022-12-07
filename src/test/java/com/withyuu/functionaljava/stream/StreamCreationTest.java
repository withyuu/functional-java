package com.withyuu.functionaljava.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamCreationTest {

    @Test
    void createEmptyStream() {
        Stream<String> streamEmpty = Stream.empty();
        List<String> emptyList = streamEmpty.collect(Collectors.toList());
        assertEquals(0, emptyList.size());
    }

    @Test
    void createWithStreamOf() {
        Stream<String> marioCharacterStream = Stream.of("Mario", "Luigi", "Peach");
        long count = marioCharacterStream.count();
        assertEquals(3, count);
    }

    @Test
    void createStreamFromArrays() {
        String[] disneyPrincessArr = {"Aurora", "Snow White", "Belle", "Jasmine"};
        Stream<String> disneyPrincessStream = Arrays.stream(disneyPrincessArr);
        disneyPrincessStream.forEach(System.out::println);
    }

    @Test
    void createStreamFromCollections() {
        List<String> starwarsPlanetArr = Arrays.asList("Tatooine", "Coruscant", "Naboo", "Hoth");
        Stream<String> starwarsPlanetStream = starwarsPlanetArr.stream();
        starwarsPlanetStream.forEach((planet) -> {
            System.out.println("Planet: " + planet);
        });
    }

    @Test
    void createPrimitiveStream() {
        IntStream intStream = IntStream.range(1, 5);
        intStream.forEach(System.out::println);
    }

}
