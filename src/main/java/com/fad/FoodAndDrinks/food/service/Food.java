package com.fad.FoodAndDrinks.food.service;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Food {
    List<com.fad.FoodAndDrinks.food.model.Food> getAllFood();

    com.fad.FoodAndDrinks.food.model.Food createFood(com.fad.FoodAndDrinks.food.model.Food food);

    com.fad.FoodAndDrinks.food.model.Food getFoodById(Long foodId)
            throws ResourceNotFoundException;

    com.fad.FoodAndDrinks.food.model.Food updateFood(Long foodId, @NotNull com.fad.FoodAndDrinks.food.model.Food foodDetails) throws ResourceNotFoundException;

    void deleteFood(Long foodId)
            throws ResourceNotFoundException;
}
