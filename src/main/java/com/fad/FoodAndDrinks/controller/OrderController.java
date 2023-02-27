package com.fad.FoodAndDrinks.controller;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.model.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {
    @Autowired
    private com.fad.FoodAndDrinks.services.Order service;
    @GetMapping("/orders")
    public List<Order> getOrders() {
        return service.getOrders();
    }

    @PostMapping("/order")
    public Order createOrder(@RequestBody @NotNull Order order) throws ResourceNotFoundException {
        return service.createOrder(order);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId)
            throws ResourceNotFoundException {
        Order order = service.getOrderById(orderId);
        return ResponseEntity.ok().body(order);
    }
}
