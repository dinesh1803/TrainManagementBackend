package org.ssb.trainrootmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.ssb.trainrootmanagement.model.Station;

public interface StationRepo extends JpaRepository<Station,Integer>{
    
}