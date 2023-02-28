package com.fad.FoodAndDrinks.user.service;

import com.auth0.jwt.exceptions.JWTCreationException;

public interface Jwt {
    String signJWT(Long userId) throws JWTCreationException;

    Long verifyJWT(String token);
}
