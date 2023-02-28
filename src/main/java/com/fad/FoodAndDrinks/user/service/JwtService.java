package com.fad.FoodAndDrinks.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class JwtService implements Jwt {
    @Value("${jwt.key}")
    private String jwtKey;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Override
    public String signJWT(Long userId) throws JWTCreationException {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        logger.error("jwtkey: " + jwtKey);

        return JWT.create()
                .withIssuer(jwtIssuer)
                .withClaim("userId", userId)
                .sign(algorithm);
    }

    @Override
    public Long verifyJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(jwtIssuer)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("userId").asLong();
    }
}
