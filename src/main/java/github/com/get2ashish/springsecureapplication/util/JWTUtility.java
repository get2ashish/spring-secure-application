package github.com.get2ashish.springsecureapplication.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JWTUtility {

    @Value("${jwt.secret}")
    String  JWT_SECRET;

    private final long JWT_TOKEN_VALIDITY = 5*60*60;


    private Claims getClaims(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(Base64.getEncoder().encode(JWT_SECRET.getBytes())).build().parseClaimsJws(jwtToken).getBody();
    }

    public Date getExpirationDate(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getUserRole(String token) {
        Claims claims = getClaims(token);
        return claims.getOrDefault("roles","USER").toString();
    }
    public Boolean isTokenValid(String jwtToken) {
        Date expirationDate = getExpirationDate(jwtToken);
        Date current = new Date();
        boolean isExpirationDateValid = current.before(expirationDate);
        log.info("Is token valid {}",isExpirationDateValid);
        return isExpirationDateValid;
    }

    public String createJWTToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject("spring-secure-app")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(JWT_SECRET.getBytes()))
                .compact();
    }
}
