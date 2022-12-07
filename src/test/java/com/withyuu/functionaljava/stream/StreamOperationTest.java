package com.withyuu.functionaljava.stream;

import com.withyuu.functionaljava.bookstore.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class StreamOperationTest {

    private List<Book> bookList;

    @BeforeEach
    void setUp() {
        bookList = Arrays.asList(
                new Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", new BigDecimal("7.19"), 224, "kid", "fantasy", "magic"),
                new Book("Harry Potter and the Chamber of Secrets", "J. K. Rowling", new BigDecimal("6.98"), 341, "kid", "fantasy", "magic"),
                new Book("Winnie the Pooh: Illustrated", "A. A. Milne", new BigDecimal("6.99"), 174, "kid", "classic", "illustrated"),
                new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", new BigDecimal("15.50"), 578, "history", "science"),
                new Book("Sapiens: A Graphic History: The Birth of Humankind", "Yuval Noah Harari", new BigDecimal("21.99"), 248, "history", "science", "illustrated"),
                new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", new BigDecimal("35.61"), 464, "programming"),
                new Book("Talk Like TED", "Carmine Gallo", new BigDecimal("9.99"), 304, "public speaking"),
                new Book("The Science of Living", "Dr. Stuart Farrimond", new BigDecimal("21.68"), 256, "science", "illustrated", "health"),
                new Book("What If?", "Randall Munroe", new BigDecimal("14.60"), 320, "humor", "science", "xkcd"),
                new Book("What If? 2", "Randall Munroe", new BigDecimal("29.41"), 368, "humor", "science", "xkcd"),
                new Book("How To", "Randall Munroe", new BigDecimal("15.27"), 308, "humor", "science", "physics", "xkcd")
        );
    }

    @Test
    void filterPriceLessThanTenDollars() {
        long count = bookList.stream()
                .filter(book -> book.getPrice().compareTo(new BigDecimal("10.00")) < 0)
                .count();
        assertEquals(4, count);
    }

    @Test
    void filterBooksForKidsLessThanSevenDollars() {
        long count = bookList.stream()
                .filter(book -> book.getTagList().contains("kid"))
                .filter(book -> book.getPrice().compareTo(new BigDecimal("7.00")) < 0)
                .count();
        assertEquals(2, count);
    }

    @Test
    void getBookAuthors() {
        List<String> authorList = bookList.stream()
                .map(book -> book.getAuthor())
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("J. K. Rowling", "J. K. Rowling", "A. A. Milne", "Yuval Noah Harari", "Yuval Noah Harari",
                "Robert C. Martin", "Carmine Gallo", "Dr. Stuart Farrimond", "Randall Munroe", "Randall Munroe", "Randall Munroe");

        assertEquals(expected, authorList);
    }

    @Test
    void getDistinctBookAuthors() {
        List<String> authorList = bookList.stream()
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("J. K. Rowling", "A. A. Milne", "Yuval Noah Harari",
                "Robert C. Martin", "Carmine Gallo", "Dr. Stuart Farrimond", "Randall Munroe");

        assertEquals(expected, authorList);
    }

    @Test
    void filterPriceLessThanTenDollarsAndPrintDetails() {
        bookList.stream()
                .filter(book -> book.getPrice().compareTo(new BigDecimal("10.00")) < 0)
                .map(book -> String.format("Title: %-45s Author: %-15s Price: %5s", book.getTitle(), book.getAuthor(), book.getPrice()))
                .forEach(System.out::println);
    }

    @Test
    void filterUniqueTagsForKidBooks() {
        List<String> tagsForKidBooks = bookList.stream()
                .filter(book -> book.getTagList().contains("kid"))
                .flatMap(book -> book.getTagList().stream())
                .distinct()
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("kid", "fantasy", "magic", "classic", "illustrated"), tagsForKidBooks);
    }

    @Test
    void sumPageLengthOfRandallBooks() {
        int sumRandallBookPageLength = bookList.stream()
                .filter(book -> book.getAuthor().equals("Randall Munroe"))
                .mapToInt(Book::getTotalPage)
                .sum();
        assertEquals(996, sumRandallBookPageLength);
    }

    @Test
    void sumPriceOfRandallBooks() {
        BigDecimal sumRandallBookPrice = bookList.stream()
                .filter(book -> book.getAuthor().equals("Randall Munroe"))
                .map(Book::getPrice)
                .reduce(
                        new BigDecimal("0.0"),
                        (partialResult, price) -> partialResult.add(price));
        assertEquals(new BigDecimal("59.28"), sumRandallBookPrice);
    }

    @Test
    void collectAuthorNameToSet() {
        Set<String> authorSet = bookList.stream()
                .map(Book::getAuthor)
                .collect(Collectors.toSet());

        Set<String> expected = new HashSet<>(Arrays.asList("J. K. Rowling", "A. A. Milne", "Yuval Noah Harari",
                "Robert C. Martin", "Carmine Gallo", "Dr. Stuart Farrimond", "Randall Munroe"));

        assertEquals(expected, authorSet);
    }

    @Test
    void collectBookNamesAsString() {
        String randallBooks = bookList.stream()
                .filter(book -> book.getAuthor().equals("Randall Munroe"))
                .map(Book::getTitle)
                .collect(Collectors.joining(",", "[", "]"));
        assertEquals("[What If?,What If? 2,How To]", randallBooks);
    }

    @Test
    void collectBookTitleGroupedByAuthor() {
        Map<String, List<String>> booksGroupedByAuthor = bookList.stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.mapping(Book::getTitle, Collectors.toList())));

        assertEquals(booksGroupedByAuthor.get("Dr. Stuart Farrimond"), Arrays.asList("The Science of Living"));
        assertEquals(booksGroupedByAuthor.get("Yuval Noah Harari"),
                Arrays.asList("Sapiens: A Brief History of Humankind", "Sapiens: A Graphic History: The Birth of Humankind"));
    }

    @Test
    void collectSumPageLengthOfRandallBooks() {
        int sumRandallBookPageLength = bookList.stream()
                .filter(book -> book.getAuthor().equals("Randall Munroe"))
                .collect(Collectors.summingInt(Book::getTotalPage));
        assertEquals(996, sumRandallBookPageLength);
    }

    @Test
    void collectStatsOfPageLengthOfRandallBooks() {
        IntSummaryStatistics statsRandallBookPageLength = bookList.stream()
                .filter(book -> book.getAuthor().equals("Randall Munroe"))
                .collect(Collectors.summarizingInt(Book::getTotalPage));

        System.out.println(statsRandallBookPageLength);
    }

    @Test
    void collectSumPageLengthByAuthor() {
        Map<String, Integer> pageLengthGroupedByAuthor = bookList.stream()
                .collect(Collector.of(
                        HashMap::new,
                        (result, book) -> {
                            Integer current = result.getOrDefault(book.getAuthor(), 0);
                            result.put(book.getAuthor(), current + book.getTotalPage());
                        },
                        (result1, result2) -> {
                            result2.forEach((key, value) -> {
                                Integer current = result1.getOrDefault(key, 0);
                                result1.put(key, current + value);
                            });
                            return result1;
                        }
                ));

        assertEquals(pageLengthGroupedByAuthor.get("J. K. Rowling"), 565);
        assertEquals(pageLengthGroupedByAuthor.get("Yuval Noah Harari"), 826);
    }

    @Test
    void anyMatchPriceOver30() {
        boolean hasPriceOver30 = bookList.stream()
                .anyMatch(book -> book.getPrice().compareTo(new BigDecimal("30.00")) > 0);
        assertTrue(hasPriceOver30);
    }

    @Test
    void findFirstScienceBook() {
        Book scienceBook = bookList.stream()
                .filter(book -> book.getTagList().contains("science"))
                .findFirst()
                .get();

        assertEquals("Sapiens: A Brief History of Humankind", scienceBook.getTitle());
    }

    @Test
    void findFirstFinanceBook() {
        Optional<Book> financeBook = bookList.stream()
                .filter(book -> book.getTagList().contains("finance"))
                .findFirst();

        assertFalse(financeBook.isPresent());
    }

    @Test
    void findFirstTravelGuideBookOrGiveMeAntarcticaTravelGuide() {
        Book travelBook = bookList.stream()
                .filter(book -> book.getTagList().contains("travel"))
                .findFirst()
                .orElse(new Book("Lonely Planet Antarctica", "Alexis Averbuck", new BigDecimal("18.44"), 224, "travel"));

        assertEquals("Lonely Planet Antarctica", travelBook.getTitle());
    }

    @Test
    void mapOptionalValue() {
        String travelBookTitle = bookList.stream()
                .filter(book -> book.getTagList().contains("travel"))
                .findFirst()
                .map(Book::getTitle)
                .orElseGet(() -> "Let's go to Japan");

        assertEquals("Let's go to Japan", travelBookTitle);
    }

}
