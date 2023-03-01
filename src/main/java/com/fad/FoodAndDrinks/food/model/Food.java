package com.fad.FoodAndDrinks.food.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(	name = "food")
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Schema(hidden = true)
    private Long id;

    private String name;

    private Float price;
}
