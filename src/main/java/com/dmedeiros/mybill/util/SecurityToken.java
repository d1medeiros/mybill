package com.dmedeiros.mybill.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dmedeiros.mybill.user.model.User;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class SecurityToken {

    private final static long TOKEN_EXPIRES_HOURS = 24;
    private final static String SECRET = "MAMAE";
    private final static String LOGIN_FIELD = "vvc13";

    private SecurityToken() {
    }


    public static synchronized String generateHash(User user) {
        Date data = Date.from(LocalDateTime.now()
                .plusHours(TOKEN_EXPIRES_HOURS)
                .atZone(ZoneId.systemDefault())
                .toInstant());

        String userId = user.getId().toString();
        String userLogin = SecurityAuxiliary.digester(user.getLogin());

        Algorithm algorithm = getAlgorithm();
        return JWT.create()
                .withClaim(LOGIN_FIELD, userLogin)
                .withSubject(userId)
//                .withExpiresAt(data)
                .sign(algorithm);
    }

    private static Algorithm getAlgorithm() {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return algorithm;
    }

    private static DecodedJWT getDecodedJWT(String token) {
        DecodedJWT jwt = null;
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        jwt = verifier.verify(token);
        return jwt;
    }

    public static Long getUserIdFromToken(String token) {

        Long userId = null;
        try {
            DecodedJWT hashIsValid = getDecodedJWT(token);
            getAlgorithm().verify(hashIsValid);
            Claim claim = hashIsValid.getClaim(LOGIN_FIELD);

            if (claim.isNull())
                throw new JWTDecodeException("Claim is null");

            userId = Long.valueOf(hashIsValid.getSubject());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    public static boolean verify(String token) {
        DecodedJWT hashIsValid = getDecodedJWT(token);
        getAlgorithm().verify(hashIsValid);
        return true;
    }

}