package ru.learnUp.lesson22.bookShop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {

    private int id;
    private String name;
    private String surname;
    private String books;
}
