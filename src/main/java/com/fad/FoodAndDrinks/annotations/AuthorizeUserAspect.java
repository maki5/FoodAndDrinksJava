package com.fad.FoodAndDrinks.annotations;

import com.fad.FoodAndDrinks.NotAuthorizedException;
import com.fad.FoodAndDrinks.user.service.Jwt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizeUserAspect {
    @Autowired
    private Jwt jwtService;

    @Around("@annotation(AuthorizeUser)")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {

        //BEFORE METHOD EXECUTION
        String token = (String) joinPoint.getArgs()[0];

        Long userId = jwtService.verifyJWT(token);
        if ( userId == null || userId == 0) {
            return new NotAuthorizedException("Invalid User. Please login with correct credential.");
        }

        return joinPoint.proceed();
    }
}
