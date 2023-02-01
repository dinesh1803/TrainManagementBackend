package org.ssb.trainrootmanagement.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ssb.trainrootmanagement.model.RouteDetails;
import org.ssb.trainrootmanagement.model.Trains;


public interface RouteDetailsRepo extends JpaRepository<RouteDetails, Integer> {

    @Query(value="select * from route_details r  inner join train_details t  on r.train_id=t.id where t.id=:trainId",nativeQuery = true)
    List<RouteDetails> findByTrainName(@Param(value = "trainId")int trainId);


    @Query("""
            select t from RouteDetails r 
            inner join Trains t on t.id = r.train 
            inner join Station s on s.id=r.station 
            where t.id = Any (select t.id from RouteDetails r 
            inner join Trains t on t.id = r.train 
            inner join Station s on s.id=r.station where s.id in(:source,:dest) GROUP BY t.id HAVING COUNT(t.id) > 1)
           """)
    Set<Trains> findByTrainId(@Param(value="source") int source ,@Param(value="dest") int dest);

}

