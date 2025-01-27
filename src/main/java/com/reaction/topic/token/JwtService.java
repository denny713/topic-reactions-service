package com.reaction.topic.token;

import com.reaction.topic.model.entity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access-exp}")
    private long accessExpire;
    @Value("${jwt.refresh-exp}")
    private long refreshExpire;
    @Value("${jwt.kid}")
    private String kid;

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("kid", kid)
                .claim("jti", UUID.randomUUID().toString())
                .claim("iss", "http://localhost:7373")
                .claim("typ", "Bearer")
                .claim("azp", UUID.randomUUID().toString())
                .claim("sid", UUID.randomUUID().toString())
                .compact();
    }

    private Claims claimToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = claimToken(token);
        return claimsResolver.apply(claims);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(extraClaims(userDetails), userDetails, accessExpire);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpire);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Object getFromExtraClaim(String token, String value) {
        return extractClaim(token, claims -> claims.get(value));
    }

    private Map<String, Object> extraClaims(UserDetails userDetails) {
        Account account = (Account) userDetails;
        Map<String, Object> result = new HashMap<>();
        result.put("name", account.getFullName());
        result.put("email", account.getEmail());
        return result;
    }
}
