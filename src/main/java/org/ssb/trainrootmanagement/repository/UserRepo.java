package org.ssb.trainrootmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.ssb.trainrootmanagement.model.LoginDetails;
import java.util.Optional;

public interface UserRepo extends JpaRepository<LoginDetails,Integer>{

    LoginDetails findByEmailAndPassword(String email, String password);

   Optional<LoginDetails> findByEmail(String username);



}