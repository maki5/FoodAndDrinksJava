package com.fad.FoodAndDrinks.controller;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.model.Drink;
import com.fad.FoodAndDrinks.repository.DrinksRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DrinkController {
    @Autowired
    private DrinksRepository repo;

    @GetMapping("/drinks")
    public List<Drink> getAllDrinks() {
        return repo.findAll();
    }

    @PostMapping("/drink")
    public Drink createDrink(@RequestBody Drink drink) {
        return repo.save(drink);
    }

    @GetMapping("/drink/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable(value = "id") Long drinkId)
            throws ResourceNotFoundException {
        Drink drink = repo.findById(drinkId)
                .orElseThrow(() -> new ResourceNotFoundException("Drink not found for this id :: " + drinkId));
        return ResponseEntity.ok().body(drink);
    }

    @PutMapping("/drink/{id}")
    public ResponseEntity<Drink> updateDrink(@PathVariable(value = "id") Long drinkId,
                                           @RequestBody @NotNull Drink drinkDetails) throws ResourceNotFoundException {
        Drink drink = repo.findById(drinkId)
                .orElseThrow(() -> new ResourceNotFoundException("Drink not found for this id :: " + drinkId));

        drink.setPrice(drinkDetails.getPrice());
        drink.setName(drinkDetails.getName());
        final Drink updatedDrink = repo.save(drink);
        return ResponseEntity.ok(updatedDrink);
    }

    @DeleteMapping("/drink/{id}")
    public Map<String, Boolean> deleteDrink(@PathVariable(value = "id") Long drinkId)
            throws ResourceNotFoundException {
        Drink drink = repo.findById(drinkId)
                .orElseThrow(() -> new ResourceNotFoundException("Drink not found for this id :: " + drinkId));

        repo.delete(drink);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
