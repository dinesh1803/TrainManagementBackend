package org.ssb.trainrootmanagement.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ssb.trainrootmanagement.model.Trains;


public interface TrainRepo extends JpaRepository<Trains,Integer>{

@Query(value=" select * from train_details r  inner join route t  on r.route_id=t.id where t.source=:source AND t.destination=:dest",nativeQuery=true)
List<Trains> findBySourceAndDest(String source ,String dest);

    
}