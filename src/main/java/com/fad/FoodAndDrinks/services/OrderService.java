package com.fad.FoodAndDrinks.services;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.model.Drink;
import com.fad.FoodAndDrinks.model.Food;
import com.fad.FoodAndDrinks.model.Order;
import com.fad.FoodAndDrinks.repository.DrinksRepository;
import com.fad.FoodAndDrinks.repository.FoodRepository;
import com.fad.FoodAndDrinks.repository.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService implements com.fad.FoodAndDrinks.services.Order {
    @Autowired
    private OrderRepository repo;

    @Autowired
    private FoodRepository foodRepo;
    @Autowired
    private DrinksRepository drinksRepo;

    @Override
    @GetMapping("/orders")
    public List<Order> getOrders() {
        return repo.findAll();
    }

    @Override
    public Order createOrder(@NotNull Order order) throws ResourceNotFoundException {
        List<Long> foodIds = order.getFoodIds();
        Set<com.fad.FoodAndDrinks.model.Food> orderedFood = new HashSet<>();
        List<Long> drinkIds = order.getDrinkIds();
        Set<com.fad.FoodAndDrinks.model.Drink> orderedDrinks = new HashSet<>();

        for (Long id : foodIds)
        {
            Food f = foodRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food not found for this id :: " + id));
            orderedFood.add(f);
        }

        order.setOrderedFood(orderedFood);

        for (Long id : drinkIds)
        {
            Drink d = drinksRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drink not found for this id :: " + id));
            orderedDrinks.add(d);
        }

        order.setOrderedDrinks(orderedDrinks);

        return repo.save(order);
    }

    @Override
    public Order getOrderById(Long orderId)
            throws ResourceNotFoundException {
        return repo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
    }
}
