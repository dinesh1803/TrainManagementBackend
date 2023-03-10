package org.ssb.trainrootmanagement.jwtutil;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.ssb.trainrootmanagement.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@Configuration
public class AuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  @Lazy
  private GenerateToken tokenProvider;
  @Autowired
  @Lazy
  private UserService userService;

  public static String getTokenPrefix = "Bearer";
  
  AntPathMatcher pathMatcher = new AntPathMatcher();
  Set<String> skipUrls = new HashSet<>();

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    skipUrls.add("/admin/login");
    skipUrls.add("/admin/signup");

    boolean matches = skipUrls.stream().anyMatch(skip -> pathMatcher.match(skip, request.getServletPath()));
    return matches;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String header = request.getHeader("Authorization");
    String mailId = null;
    String authToken = null;
    authToken = header.replace(getTokenPrefix, "");
    try {
      mailId = tokenProvider.getEmailFromToken(authToken);


    UserDetails userDetails = userService.loadUserByUsername(mailId);

    if (tokenProvider.validateToken(authToken, userDetails)) {
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities());
      authentication.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request));
      logger.info(
          "authenticated user " + mailId + ", setting security context");
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
    } else {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write("{\"message\":\"Unauthorized User\"}");
    }
  }catch(Exception e){
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.getWriter().write("{\"message\":\"Unauthorized User\"}");
  }
  }
}
