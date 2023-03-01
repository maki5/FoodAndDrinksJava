package com.fad.FoodAndDrinks.drinks.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "drinks")
@Data
@NoArgsConstructor
public class Drink {

    public Drink(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Float price;
}
