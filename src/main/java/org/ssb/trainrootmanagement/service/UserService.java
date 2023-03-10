
package org.ssb.trainrootmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ssb.trainrootmanagement.exception.AlreadyExistException;
import org.ssb.trainrootmanagement.jwtutil.AuthenticationUtil;
import org.ssb.trainrootmanagement.model.LoginDetails;
import org.ssb.trainrootmanagement.repository.UserRepo;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationUtil authenticationUtil;

    public LoginDetails save(LoginDetails user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<LoginDetails> getAll() {
        return userRepo.findAll();
    }

    public void deleteById(int id) {
        userRepo.deleteById(id);
    }

    public Optional<LoginDetails> updateById(LoginDetails user) {
        if (userRepo.existsById(user.getId())) {
            return Optional.of(userRepo.save(user));
        } else {
            return null;
        }
    }

    public String login(LoginDetails user) throws AlreadyExistException {

        LoginDetails log = userRepo.findByEmail(user.getEmail()).orElse(null);

        if (log == null) {
            throw new AlreadyExistException("Invalid mailId or password");
        }
        else if(bCryptPasswordEncoder.matches(log.getPassword(), user.getPassword())){
            throw new AlreadyExistException("Invalid  password");
    }
        return authenticationUtil.authenticate(user.getEmail(),user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        LoginDetails login = userRepo.findByEmail(username).orElse(null);

        return (UserDetails) login;
    }


}