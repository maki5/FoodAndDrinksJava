package com.fad.FoodAndDrinks.annotations;

import com.fad.FoodAndDrinks.NotAuthorizedException;
import com.fad.FoodAndDrinks.user.service.Jwt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthorizeUserAspect {
    @Autowired
    private Jwt jwtService;
    Logger logger = LoggerFactory.getLogger(AuthorizeUserAspect.class);

    @Around("@annotation(AuthorizeUser)")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest req = getRequest();

        //BEFORE METHOD EXECUTION
        String token = req.getHeader("Authorization");
        if (token == null) {
            return new NotAuthorizedException("No token provided.");
        }

        token = token.replaceFirst("Bearer ", "");

        Long userId = jwtService.verifyJWT(token);
        if ( userId == null || userId == 0) {
            return new NotAuthorizedException("Invalid User. Please login with correct credential.");
        }

        return joinPoint.proceed();
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest();
    }
}
