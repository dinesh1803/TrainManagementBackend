package org.ssb.trainrootmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.ssb.trainrootmanagement.model.LoginDetails;


public interface UserRepo extends JpaRepository<LoginDetails,Integer>{



}