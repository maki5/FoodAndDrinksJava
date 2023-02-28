package com.fad.FoodAndDrinks.order.service;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface Order {
    @GetMapping("/orders")
    List<com.fad.FoodAndDrinks.order.model.Order> getOrders();

    com.fad.FoodAndDrinks.order.model.Order createOrder(@NotNull com.fad.FoodAndDrinks.order.model.Order order) throws ResourceNotFoundException;

    com.fad.FoodAndDrinks.order.model.Order getOrderById(Long orderId)
            throws ResourceNotFoundException;
}
