package com.fad.FoodAndDrinks.controller;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.model.Food;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FoodController {
    @Autowired
    private com.fad.FoodAndDrinks.services.Food service;
    @GetMapping("/food")
    public List<Food> getAllFood() {
        return service.getAllFood();
    }

    @PostMapping("/food")
    public Food createFood(@RequestBody Food food) {
        return service.createFood(food);
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable(value = "id") Long foodId)
            throws ResourceNotFoundException {
        Food food = service.getFoodById(foodId);

        return ResponseEntity.ok().body(food);
    }

    @PutMapping("/food/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable(value = "id") Long foodId,
                                           @RequestBody @NotNull Food foodDetails) throws ResourceNotFoundException {
        final Food updatedFood = service.updateFood(foodId, foodDetails);
        return ResponseEntity.ok(updatedFood);
    }

    @DeleteMapping("/food/{id}")
    public Map<String, Boolean> deleteFood(@PathVariable(value = "id") Long foodId)
            throws ResourceNotFoundException {

        service.deleteFood(foodId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
