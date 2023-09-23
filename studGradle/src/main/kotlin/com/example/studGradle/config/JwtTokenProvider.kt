package com.example.studGradle.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(private val secret: String = "your-secret-keyytrbvrfdrvergergerveegvddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd") {
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(username: String): String {
        println("/n **************** Token Genarated *******************/n")
        val expiration = Date(Date().time+ 60*60 * 1000) // Token expiration time (1 day)
        return Jwts.builder()
            .setSubject(username)
           // .claim("authorities", authorities.map { it.authority })
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            // Token is invalid
            println("token is inavlid ************************************")
            return false
        }
    }

    fun extractUsername(token: String): String {
        val claims: Claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

    fun expireToken(token: String): String {
        // Create a new token with an immediate expiration (0 seconds)
        val expiration = Date(0)
        return Jwts.builder()
            .setSubject(extractUsername(token)) // Use the same username
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

//    fun extractAuthorities(token: String): Collection<GrantedAuthority> {
//        val claims: Claims = Jwts.parserBuilder()
//            .setSigningKey(key)
//            .build()
//            .parseClaimsJws(token)
//            .body
//
//        val authorities = claims["authorities"] as List<String>?
//        return authorities?.map { SimpleGrantedAuthority(it) } ?: emptyList()
//    }
}
