package com.fad.FoodAndDrinks;

public class NotAuthorizedException extends Exception{
    public NotAuthorizedException(String msg) {
        super(msg);
    }
}
