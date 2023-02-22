package com.fad.FoodAndDrinks.repository;

import com.fad.FoodAndDrinks.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksRepository extends JpaRepository<Drink, Long> {

}
