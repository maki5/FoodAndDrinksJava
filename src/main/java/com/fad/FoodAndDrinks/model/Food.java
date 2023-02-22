package com.fad.FoodAndDrinks.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "food")
@Data
@NoArgsConstructor
public class Food {
    public Food(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Float price;
}
