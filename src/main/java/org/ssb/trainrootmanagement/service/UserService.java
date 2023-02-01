
package org.ssb.trainrootmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.ssb.trainrootmanagement.model.LoginDetails;
import org.ssb.trainrootmanagement.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public LoginDetails save(LoginDetails user) {
    
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

   
}