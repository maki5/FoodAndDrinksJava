package com.fad.FoodAndDrinks.controller;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.model.Drink;
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
    private com.fad.FoodAndDrinks.services.Drink service;

    @GetMapping("/drinks")
    public List<Drink> getAllDrinks() {
        return service.getDrinks();
    }

    @PostMapping("/drink")
    public Drink createDrink(@RequestBody Drink drink) {
        return service.createDrink(drink);
    }

    @GetMapping("/drink/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable(value = "id") Long drinkId)
            throws ResourceNotFoundException {
        Drink drink = service.getDrinkById(drinkId);
        return ResponseEntity.ok().body(drink);
    }

    @PutMapping("/drink/{id}")
    public ResponseEntity<Drink> updateDrink(@PathVariable(value = "id") Long drinkId,
                                           @RequestBody @NotNull Drink drinkDetails) throws ResourceNotFoundException {
        final Drink updatedDrink = service.updateDrink(drinkId, drinkDetails);
        return ResponseEntity.ok(updatedDrink);
    }

    @DeleteMapping("/drink/{id}")
    public Map<String, Boolean> deleteDrink(@PathVariable(value = "id") Long drinkId)
            throws ResourceNotFoundException {

        service.deleteDrink(drinkId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
