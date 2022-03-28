package ru.learnUp.lesson22.bookShop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private int id;
    private String userName;
    private String bookName;
    private int count;
}
