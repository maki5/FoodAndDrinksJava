package com.fad.FoodAndDrinks.user.service;

import com.fad.FoodAndDrinks.ResourceNotFoundException;

public interface User {
    String createUser(com.fad.FoodAndDrinks.user.model.User user);

    String login(com.fad.FoodAndDrinks.user.model.User user) throws ResourceNotFoundException;
}
