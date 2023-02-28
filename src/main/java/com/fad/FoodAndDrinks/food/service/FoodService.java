package com.fad.FoodAndDrinks.food.service;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.food.model.Food;
import com.fad.FoodAndDrinks.food.repository.FoodRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService implements com.fad.FoodAndDrinks.food.service.Food {
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
