package com.fad.FoodAndDrinks.controller;

import com.fad.FoodAndDrinks.model.Food;
import com.fad.FoodAndDrinks.repository.FoodRepository;
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
    private FoodRepository repo;

    @GetMapping("/food")
    public List<Food> getAllFood() {
        return repo.findAll();
    }

    @PostMapping("/food")
    public Food createFood(@RequestBody Food food) {
        return repo.save(food);
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable(value = "id") Long foodId)
            throws Exception {
        Food food = repo.findById(foodId)
                .orElseThrow(() -> new Exception("Food not found for this id :: " + foodId));
        return ResponseEntity.ok().body(food);
    }

    @PutMapping("/food/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable(value = "id") Long foodId,
                                           @RequestBody @NotNull Food foodDetails) throws Exception {
        Food food = repo.findById(foodId)
                .orElseThrow(() -> new Exception("Food not found for this id :: " + foodId));

        food.setPrice(foodDetails.getPrice());
        food.setName(foodDetails.getName());
        final Food updatedFood = repo.save(food);
        return ResponseEntity.ok(updatedFood);
    }

    @DeleteMapping("/food/{id}")
    public Map<String, Boolean> deleteFood(@PathVariable(value = "id") Long foodId)
            throws Exception {
        Food food = repo.findById(foodId)
                .orElseThrow(() -> new Exception("Food not found for this id :: " + foodId));

        repo.delete(food);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
