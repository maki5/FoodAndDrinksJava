package com.fad.FoodAndDrinks;

public class NotAuthorizedException extends RuntimeException{
    public NotAuthorizedException(String msg) {
        super(msg);
    }
}
