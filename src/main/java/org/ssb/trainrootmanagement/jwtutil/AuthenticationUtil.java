package org.ssb.trainrootmanagement.jwtutil;


import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class AuthenticationUtil {
    @Autowired
    @Lazy
    private  AuthenticationManager authenticationManager;
    @Autowired
    private GenerateToken generateToken;

  //   public void AuthenticationManager(
  //   AuthenticationManager authenticationManager,
  //   GenerateToken generateToken
  // ) {
  //   this.generateToken = generateToken;
  //   this.authenticationManager = authenticationManager;
  // }
   
public String authenticate(String email,String password){
try {
   final Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    SecurityContextHolder.getContext().setAuthentication(auth);
    return generateToken.generateToken(auth, email, password);

} catch (Exception e) {
    e.printStackTrace();
}
return null;
}
}