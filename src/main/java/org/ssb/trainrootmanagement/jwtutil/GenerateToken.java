package org.ssb.trainrootmanagement.jwtutil;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.ssb.trainrootmanagement.model.LoginDetails;

@Component
public class GenerateToken {
  private static final String KEY = "mykeydfcgvbnmcbnmmvjhbhvbjhvbeuuhgbuduhbkjdhbskjdhbskjdhbKJDCkjdhhbjbhbjhjhgfhghdhcgvnggfffhgfhgrdyreyetretre";
 long currentDate = System.currentTimeMillis();
//4 days
 long expire = System.currentTimeMillis()+259200000;


    public String generateToken(Authentication auth ,String email,String password) {

      try{
      Date issuedDate=new Date(currentDate);
      Date expireDate=new Date(expire);

      String authenticate=auth.getAuthorities().stream().map(p->p.getAuthority()).collect(Collectors.joining(","));
     System.out.println(authenticate+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

      
      String res=  Jwts.builder().setSubject(email).setId(password)
      .claim("auth", authenticate)
      .setIssuedAt(issuedDate).setExpiration(expireDate).signWith(Keys.hmacShaKeyFor(KEY.getBytes())).compact();

      return res;
      }catch(Exception e){
        e.printStackTrace();
     System.out.println("========================"+e.getMessage());   
        return "";
      }
    }


   
  public String getEmailFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(
    String token,
    Function<Claims, T> claimsResolver
  ) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }
  private Claims getAllClaimsFromToken(String token) {
    // SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    return Jwts.parserBuilder()
          .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
          .build().parseClaimsJws(token).getBody();
  }


    public boolean validateToken(String token, UserDetails user) {
      final String mailId = getEmailFromToken(token);
      return (
        mailId.equals(((LoginDetails) user).getEmail()) &&
        !isTokenExpired(token)
      );
    }


  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }
}
