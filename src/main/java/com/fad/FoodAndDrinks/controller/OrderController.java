package com.fad.FoodAndDrinks.controller;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.model.Drink;
import com.fad.FoodAndDrinks.model.Food;
import com.fad.FoodAndDrinks.model.Order;
import com.fad.FoodAndDrinks.repository.DrinksRepository;
import com.fad.FoodAndDrinks.repository.FoodRepository;
import com.fad.FoodAndDrinks.repository.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
public class OrderController {
    @Autowired
    private OrderRepository repo;

    @Autowired
    private FoodRepository foodRepo;
    @Autowired
    private DrinksRepository drinksRepo;

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return repo.findAll();
    }

    @PostMapping("/order")
    public Order createOrder(@RequestBody @NotNull Order order) throws ResourceNotFoundException {
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

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId)
            throws Exception {
        Order order = repo.findById(orderId)
                .orElseThrow(() -> new Exception("Order not found for this id :: " + orderId));
        return ResponseEntity.ok().body(order);
    }
}
