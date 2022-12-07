package com.withyuu.functionaljava.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StreamCharacteristicsTest {


    @Test()
    void streamCannotBeReused() {
        Stream<String> worldCupTeam = Stream.of("Brazil", "Japan", "Portugal", "Cameroon", "England");
        worldCupTeam.forEach(System.out::println);
        assertThrows(IllegalStateException.class, () -> {
            worldCupTeam.forEach(System.out::println);
        });
    }

    @Test
    void lazyInvocation() {
        IntStream.range(1, 10)
                .filter(i -> {
                    System.out.println("Too lazy to filter");
                    return i % 2 == 0;
                });

        IntStream.range(1, 10)
                .filter(i -> {
                    System.out.println("Once terminal operation comes, I'll do the job.");
                    return i % 2 == 0;
                })
                .count();
    }

    @Test
    void verticallyExecuted() {
        IntStream.range(1, 10)
                .filter(i -> {
                    System.out.printf("Filtering %d%n", i);
                    return i % 2 == 0;
                })
                .map(i -> {
                    System.out.printf("Mapping %d%n", i);
                    return i + 1;
                })
                .count();
    }

    @Test
    void orderMatters() {
        final Counter counter1 = new Counter();
        final Counter counter2 = new Counter();

        IntStream.range(0, 10)
                .map(i -> {
                    counter1.increment();
                    return i + 1;
                })
                .filter(i -> {
                    counter1.increment();
                    return i % 2 == 0;
                })
                .forEach(i -> { /* no-op */ });

        IntStream.range(0, 10)
                .filter(i -> {
                    counter2.increment();
                    return i % 2 == 0;
                })
                .map(i -> {
                    counter2.increment();
                    return i + 1;
                })
                .forEach(i -> { /* no-op */ });

        assertEquals(20, counter1.getCount());
        assertEquals(15, counter2.getCount());
    }

    @Test
    void blockOperation() {
        Stream.of("Australia", "Korea", "Myanmar", "Singapore", "Malaysia", "Belgium", "England")
                .filter(s -> {
                    System.out.printf("Filtering %s%n", s);
                    return s.startsWith("M");
                })
                .sorted() // blocking next operation
                .map(s -> {
                    System.out.printf("Mapping %s%n", s);
                    return "Country: " + s;
                })
                .count();
    }

    @Test
    void sequentialStream() {
        long start = System.currentTimeMillis();
        int sum = IntStream.range(0, 10)
                .map(this::slowPlusTenOperation)
                .sum();
        long end = System.currentTimeMillis();

        System.out.println("Time taken " + (end - start) + " ms");
        System.out.println("Result " + sum);
    }

    @Test
    void parallelStream() {
        long start = System.currentTimeMillis();
        int sum = IntStream.range(0, 10)
                .parallel()
                .map(this::slowPlusTenOperation)
                .sum();
        long end = System.currentTimeMillis();

        System.out.println("Time taken " + (end - start) + " ms");
        System.out.println("Result " + sum);
    }

    @Test
    void reduceAndParallelStream() {
        int reducedSequentialInitZero = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .stream()
                .reduce(0,
                        (a, b) -> a + b,
                        (a, b) -> a + b
                );
        assertEquals(55, reducedSequentialInitZero);

        int reducedSequentialInitTen = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .stream()
                .reduce(10,
                        (a, b) -> a + b,
                        (a, b) -> a + b
                );
        assertEquals(65, reducedSequentialInitTen);

        int reducedParallelInitTen = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .parallelStream()
                .reduce(10,
                        (a, b) -> a + b,
                        (a, b) -> a + b
                );
        assertEquals(155, reducedParallelInitTen);
    }

    private int slowPlusTenOperation(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        return i + 10;
    }


    private static class Counter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public int getCount() {
            return this.count;
        }
    }

}
