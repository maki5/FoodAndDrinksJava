package com.fad.FoodAndDrinks.drinks.repository;

import com.fad.FoodAndDrinks.drinks.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksRepository extends JpaRepository<Drink, Long> {

}
