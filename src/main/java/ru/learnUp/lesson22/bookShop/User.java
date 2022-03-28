package ru.learnUp.lesson22.bookShop;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {

    private int id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String address;
    private String userOrdersIds;
}
