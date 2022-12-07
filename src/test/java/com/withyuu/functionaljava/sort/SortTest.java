package com.withyuu.functionaljava.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortTest {

    @Test
    void normalSort() {
        String[] nameList = {"Potter", "Granger", "Weasley", "Snape", "Riddle", "Malfoy", "Dumbledore"};
        Arrays.sort(nameList);
        String[] expected = {"Dumbledore", "Granger", "Malfoy", "Potter", "Riddle", "Snape", "Weasley"};
        assertArrayEquals(expected, nameList);
    }

    @Test
    void sortByWordLengthComparator() {
        String[] nameList = {"Potter", "Granger", "Weasley", "Snape", "Riddle", "Malfoy", "Dumbledore"};
        Arrays.sort(nameList, new WordLengthComparator());
        String[] expected = {"Snape", "Malfoy", "Potter", "Riddle", "Granger", "Weasley", "Dumbledore"};
        assertArrayEquals(expected, nameList);
    }

    @Test
    void biasSortByAnonymousInnerClass() {
        String[] nameList = {"Potter", "Granger", "Weasley", "Snape", "Riddle", "Malfoy", "Dumbledore"};
        Arrays.sort(nameList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // This will always put Potter first
                if (s1.equals("Potter")) {
                    return -1;
                }
                if (s2.equals("Potter")) {
                    return 1;
                }
                return s1.compareTo(s2);
            }
        });
        String[] expected = {"Potter", "Dumbledore", "Granger", "Malfoy", "Riddle", "Snape", "Weasley"};
        assertArrayEquals(expected, nameList);
    }

    private static class WordLengthComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            int lengthCompareResult = Integer.compare(s1.length(), s2.length());
            return lengthCompareResult != 0 ? lengthCompareResult : s1.compareTo(s2);
        }
    }

}
