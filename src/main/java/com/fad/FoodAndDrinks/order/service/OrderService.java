package com.fad.FoodAndDrinks.order.service;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.drinks.model.Drink;
import com.fad.FoodAndDrinks.food.model.Food;
import com.fad.FoodAndDrinks.order.model.Order;
import com.fad.FoodAndDrinks.drinks.repository.DrinksRepository;
import com.fad.FoodAndDrinks.food.repository.FoodRepository;
import com.fad.FoodAndDrinks.order.repository.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService implements com.fad.FoodAndDrinks.order.service.Order {
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
        Set<Food> orderedFood = new HashSet<>();
        List<Long> drinkIds = order.getDrinkIds();
        Set<Drink> orderedDrinks = new HashSet<>();

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
