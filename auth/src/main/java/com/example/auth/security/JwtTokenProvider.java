package com.example.auth.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.example.auth.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
  
  @Value("${security.jwt.token.secret_key:key_microservices}")
  private String secretKey;

  @Value("${security.jwt.token.expire_time:3600000}")
  private long validityInMilliseconds = 3600000; // 1 hora

  @Autowired
  private MyUserDetails myUserDetails;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String username, List<Role> roles) {

    Claims claims = Jwts.claims().setSubject(username);
    claims.put("auth", roles.stream()
      .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
      .filter(Objects::nonNull)
      .collect(Collectors.toList()));
    
      Date now = new Date();
      Date validity = new Date(now.getTime() + validityInMilliseconds);

      return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));

    return new UsernamePasswordAuthenticationToken(userDetails,  "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser()
      .setSigningKey(secretKey)
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
