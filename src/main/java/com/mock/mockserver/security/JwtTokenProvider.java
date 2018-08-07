package com.mock.mockserver.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by mayank.chaurasia on 31-07-2018.
 */
@Component
public class JwtTokenProvider{

  private static final long  jwtExpirationInMs = 365 * 24 * 60 * 60;
  private static final String  jwtSecret = "secret";
  public String generateToken(Authentication authentication) {

      UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

      Date now = new Date();
      Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

      return Jwts.builder()
          .setSubject(Long.toString(userPrincipal.getId()))
          .setIssuedAt(new Date())
          //.setExpiration(expiryDate)
          .signWith(SignatureAlgorithm.HS512, jwtSecret)
          .compact();
    }

    public Long getUserIdFromJWT(String token) {
      Claims claims = Jwts.parser()
          .setSigningKey(jwtSecret)
          .parseClaimsJws(token)
          .getBody();

      return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
      try {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        return true;
      } catch (SignatureException ex) {
        System.out.println("Invalid JWT signature");
      } catch (MalformedJwtException ex) {
        System.out.println("Invalid JWT token");
      } catch (ExpiredJwtException ex) {
        System.out.println("Expired JWT token");
      } catch (UnsupportedJwtException ex) {
        System.out.println("Unsupported JWT token");
      } catch (IllegalArgumentException ex) {
        System.out.println("JWT claims string is empty.");
      }
      return false;
    }
}
