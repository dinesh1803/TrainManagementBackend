package org.ssb.trainrootmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.ssb.trainrootmanagement.model.TrainStatus;


public interface StatusRepo extends JpaRepository<TrainStatus,Integer>{
    
}