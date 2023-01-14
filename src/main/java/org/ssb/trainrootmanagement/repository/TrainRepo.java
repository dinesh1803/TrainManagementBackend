package org.ssb.trainrootmanagement.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.ssb.trainrootmanagement.model.Train;


public interface TrainRepo extends JpaRepository<Train,Integer>{



    
}