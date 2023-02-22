package com.fad.FoodAndDrinks.services;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Drink {
    List<com.fad.FoodAndDrinks.model.Drink> getDrinks();
    com.fad.FoodAndDrinks.model.Drink createDrink(com.fad.FoodAndDrinks.model.Drink drink);
    com.fad.FoodAndDrinks.model.Drink getDrinkById(Long drinkId) throws ResourceNotFoundException;
    com.fad.FoodAndDrinks.model.Drink updateDrink(Long drinkId, @NotNull com.fad.FoodAndDrinks.model.Drink drinkDetails) throws ResourceNotFoundException;
    void deleteDrink(Long drinkId) throws ResourceNotFoundException;

}
