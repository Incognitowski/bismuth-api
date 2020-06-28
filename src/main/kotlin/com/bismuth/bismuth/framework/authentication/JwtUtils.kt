package com.bismuth.bismuth.framework.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.bismuth.bismuth.user.User
import java.util.*

class JwtUtils {

    companion object {

        private const val issuer: String = "Bismuth";
        private val algorithm: Algorithm = Algorithm.HMAC256("3VsowAIgHnkQPsCldydQGkBFDiEAej0wz7CoGw9R");

        fun createJWT(user: User): String {
            val calendarInstance = Calendar.getInstance();
            calendarInstance.add(Calendar.YEAR, 1);
            val todayPlusOneYear = calendarInstance.time;
            return JWT.create()
                    .withSubject(user.username)
                    .withKeyId(user.user_id.toString())
                    .withIssuedAt(Date())
                    .withExpiresAt(todayPlusOneYear)
                    .withIssuer(issuer)
                    .sign(algorithm);
        }

        fun retrieveTokenInformation(jwt: String): DecodedJWT {
            val jwtVerifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            return jwtVerifier.verify(jwt);
        }

    }

}