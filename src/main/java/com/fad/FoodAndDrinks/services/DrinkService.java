package com.fad.FoodAndDrinks.services;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.model.Drink;
import com.fad.FoodAndDrinks.repository.DrinksRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService  implements com.fad.FoodAndDrinks.services.Drink {
    @Autowired
    private DrinksRepository repo;

    public List<Drink> getDrinks() {
        return repo.findAll();
    }

    public Drink createDrink(Drink drink) {
        return repo.save(drink);
    }

    public Drink getDrinkById(Long drinkId) throws ResourceNotFoundException {
        Drink drink = repo.findById(drinkId)
                .orElseThrow(() -> new ResourceNotFoundException("Drink not found for this id :: " + drinkId));
        return drink;
    }

    public Drink updateDrink(Long drinkId, @NotNull Drink drinkDetails) throws ResourceNotFoundException {
        Drink drink = repo.findById(drinkId)
                .orElseThrow(() -> new ResourceNotFoundException("Drink not found for this id :: " + drinkId));

        drink.setPrice(drinkDetails.getPrice());
        drink.setName(drinkDetails.getName());
        return repo.save(drink);
    }

    public void deleteDrink(Long drinkId) throws ResourceNotFoundException {
        Drink drink = repo.findById(drinkId)
                .orElseThrow(() -> new ResourceNotFoundException("Drink not found for this id :: " + drinkId));

        repo.delete(drink);
    }
}
