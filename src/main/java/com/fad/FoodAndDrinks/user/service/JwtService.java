package com.fad.FoodAndDrinks.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class JwtService implements Jwt {
    @Value("${jwt.key}")
    private String jwtKey;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    @Override
    public String signJWT(Long userId) throws JWTCreationException {
        Algorithm algorithm = Algorithm.HMAC256(jwtKey);
        log.debug("jwtkey: {}", jwtKey);

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
