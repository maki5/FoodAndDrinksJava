package com.fad.FoodAndDrinks.services;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Food {
    List<com.fad.FoodAndDrinks.model.Food> getAllFood();

    com.fad.FoodAndDrinks.model.Food createFood(com.fad.FoodAndDrinks.model.Food food);

    com.fad.FoodAndDrinks.model.Food getFoodById(Long foodId)
            throws ResourceNotFoundException;

    com.fad.FoodAndDrinks.model.Food updateFood(Long foodId, @NotNull com.fad.FoodAndDrinks.model.Food foodDetails) throws ResourceNotFoundException;

    void deleteFood(Long foodId)
            throws ResourceNotFoundException;
}
