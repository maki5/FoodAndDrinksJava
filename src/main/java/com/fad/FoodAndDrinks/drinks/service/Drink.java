package com.fad.FoodAndDrinks.drinks.service;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Drink {
    List<com.fad.FoodAndDrinks.drinks.model.Drink> getDrinks();
    com.fad.FoodAndDrinks.drinks.model.Drink createDrink(com.fad.FoodAndDrinks.drinks.model.Drink drink);
    com.fad.FoodAndDrinks.drinks.model.Drink getDrinkById(Long drinkId) throws ResourceNotFoundException;
    com.fad.FoodAndDrinks.drinks.model.Drink updateDrink(Long drinkId, @NotNull com.fad.FoodAndDrinks.drinks.model.Drink drinkDetails) throws ResourceNotFoundException;
    void deleteDrink(Long drinkId) throws ResourceNotFoundException;

}
