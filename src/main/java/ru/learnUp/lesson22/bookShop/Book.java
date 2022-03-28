package ru.learnUp.lesson22.bookShop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private String bookName;
    private String authorName;
    private double price;
}
