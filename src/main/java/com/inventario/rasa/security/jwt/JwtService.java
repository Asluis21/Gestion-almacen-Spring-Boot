package com.inventario.rasa.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${inventario.app.jwtExpirationMs}")
    private int jwtExpirationMs;
    

    @Value("${inventario.app.secret_key}")
    private String secretKey;

    public String getToken(UserDetails userDetails){

        Map<String, Object> roles = new HashMap<>();

        userDetails.getAuthorities().stream()
            .forEach(rol -> {
                roles.put(rol.getAuthority(), "true");
                roles.put("rol", rol.getAuthority());
                roles.put("username", userDetails.getUsername());
        });


        return getToken(roles, userDetails);

    }


    private String getToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())       
            .setIssuedAt(new Date(System.currentTimeMillis()))   
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build()
            .parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String token){

        try{
            Jwts.parserBuilder().setSigningKey(getKey()).build().parse(token);
            return true;
        }catch(MalformedJwtException e){
            logger.error("Invalid JWT token: ", e.getMessage());
        }catch(ExpiredJwtException e){
            logger.error("JWT token is expired: ", e.getMessage());
        }catch(UnsupportedJwtException e){
            logger.error("JWT token is unsupported: ", e.getMessage());
        }catch(IllegalArgumentException e){
            logger.error("JWT claims string is empty: ", e.getMessage());
        }

        return false;
    }
}
