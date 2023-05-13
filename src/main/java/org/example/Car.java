package org.example;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    private String brand;
    private int year;
    private String color;
    private String country;

}
