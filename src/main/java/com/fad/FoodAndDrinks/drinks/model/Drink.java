package com.fad.FoodAndDrinks.drinks.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(	name = "drinks")
@NoArgsConstructor
public class Drink {

    public Drink(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Schema(hidden = true)
    private Long id;
    private String name;
    private Float price;
}
