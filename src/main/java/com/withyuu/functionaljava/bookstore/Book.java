package com.withyuu.functionaljava.bookstore;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Book {

    private String title;
    private String author;
    private BigDecimal price;
    private int totalPage;
    private List<String> tagList;

    public Book(String title, String author, BigDecimal price, int totalPage, String... tagList) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.totalPage = totalPage;
        this.tagList = Arrays.asList(tagList);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<String> getTagList() {
        return tagList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", totalPage=" + totalPage +
                ", tagList=" + tagList +
                '}';
    }
}
