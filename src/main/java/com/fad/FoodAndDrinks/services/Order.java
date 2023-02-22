package com.fad.FoodAndDrinks.services;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface Order {
    @GetMapping("/orders")
    List<com.fad.FoodAndDrinks.model.Order> getOrders();

    com.fad.FoodAndDrinks.model.Order createOrder(@NotNull com.fad.FoodAndDrinks.model.Order order) throws ResourceNotFoundException;

    com.fad.FoodAndDrinks.model.Order getOrderById(Long orderId)
            throws ResourceNotFoundException;
}
