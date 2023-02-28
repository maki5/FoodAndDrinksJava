package com.fad.FoodAndDrinks.user.service;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.user.model.User;
import com.fad.FoodAndDrinks.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements com.fad.FoodAndDrinks.user.service.User {
    @Autowired
    private UserRepository repo;

    @Autowired
    private Jwt jwtService;

    @Override
    public String createUser(User user) {
        User u = repo.save(user);
        return jwtService.signJWT(u.getId());
    }

    @Override
    public String login(User user) throws ResourceNotFoundException {
        User u = repo.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for email : " + user.getEmail()));

        if (!u.getPassword().equals(user.getPassword())) {
            throw  new ResourceNotFoundException("Password is invalid for user : " + user.getEmail());
        }

        return jwtService.signJWT(u.getId());
    }

}
