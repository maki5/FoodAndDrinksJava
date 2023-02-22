package com.fad.FoodAndDrinks.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String buyerName;
    private String address;
    private Float price;

    @Transient
    private List<Long> foodIds;
    @Transient
    private List<Long> drinkIds;

    @ManyToMany
    @JoinTable(
            name = "order_food",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Food> orderedFood;

    @ManyToMany
    @JoinTable(
            name = "order_drinks",
            joinColumns = @JoinColumn(name = "drink_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Drink> orderedDrinks;
}
