package com.fad.FoodAndDrinks.user.service;

import com.fad.FoodAndDrinks.ResourceNotFoundException;
import com.fad.FoodAndDrinks.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements User {
    @Autowired
    private UserRepository repo;

    @Autowired
    private Jwt jwtService;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public String createUser(com.fad.FoodAndDrinks.user.model.User user) {
        com.fad.FoodAndDrinks.user.model.User u = repo.save(user);
        return jwtService.signJWT(u.getId());
    }

    @Override
    public String login(com.fad.FoodAndDrinks.user.model.User user) throws ResourceNotFoundException {
        com.fad.FoodAndDrinks.user.model.User u = repo.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for email : " + user.getEmail()));

        if (!u.getPassword().equals(user.getPassword())) {
            throw  new ResourceNotFoundException("Password is invalid for user : " + user.getEmail());
        }

        return jwtService.signJWT(u.getId());
    }

}
