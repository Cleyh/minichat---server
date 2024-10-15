package com.mcug.minichat.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
        private static final String SECRET_KEY = "MySecretKey";
        private static final long EXPIRATION_TIME = 86400000; // 1天

        public static String generateToken(String userId) {
            Date issuedAt = new Date();
            Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);

            return JWT.create()
                    .withIssuedAt(issuedAt)
                    .withExpiresAt(expiresAt)
                    .withClaim("user_id", userId)
                    .sign(Algorithm.HMAC256(SECRET_KEY));
        }

        public static Map<String, Object> isValidToken(String token) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
                JWTVerifier verifier = JWT.require(algorithm)
                        .build(); //Reusable verifier instance
                DecodedJWT jwt = verifier.verify(token);

                // 检查 token 中是否包含特定的声明
                String userId = jwt.getClaim("user_id").asString();

                if (userId.isEmpty()) {
                    return null;
                }
                // 如果所有声明都存在，返回包含这些声明的 Map
                Map<String, Object> claims = new HashMap<>();
                claims.put("user_id", userId);

                return claims;
            } catch (JWTVerificationException exception){
                //Invalid signature/claims
                return null;
            }
        }

        // Optional: Methods to extract information from token
        public static String getIdFromToken(String token) {
            return JWT.decode(token).getClaim("user_id").asString();
        }
    }

