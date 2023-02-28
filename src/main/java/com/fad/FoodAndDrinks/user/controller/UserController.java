package com.fad.FoodAndDrinks.user.controller;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController  {

    @Autowired
    private com.fad.FoodAndDrinks.user.service.User service;

    @PostMapping("/sign_up")
    public String signUp(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("/sign_in")
    public String signIn(@RequestBody User user) throws ResourceNotFoundException {
        return service.login(user);
    }
}
