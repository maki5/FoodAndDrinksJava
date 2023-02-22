package com.fad.FoodAndDrinks.services;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.model.Food;
import com.fad.FoodAndDrinks.repository.FoodRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FoodService implements com.fad.FoodAndDrinks.services.Food {
    @Autowired
    private FoodRepository repo;

    @Override
    public List<Food> getAllFood() {
        return repo.findAll();
    }

    @Override
    public Food createFood(Food food) {
        return repo.save(food);
    }

    @Override
    public Food getFoodById(Long foodId)
            throws ResourceNotFoundException {
        return repo.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id :: " + foodId));
    }

    @Override
    public Food updateFood(Long foodId, @NotNull Food foodDetails) throws ResourceNotFoundException {
        Food food = repo.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id :: " + foodId));

        food.setPrice(foodDetails.getPrice());
        food.setName(foodDetails.getName());
        return repo.save(food);
    }

    @Override
    public void deleteFood(Long foodId)
            throws ResourceNotFoundException {
        Food food = repo.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found for this id :: " + foodId));

        repo.delete(food);
    }
}
