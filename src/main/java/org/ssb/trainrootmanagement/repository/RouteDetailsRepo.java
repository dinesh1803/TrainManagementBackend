package org.ssb.trainrootmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.ssb.trainrootmanagement.model.RouteDetails;


@EnableJpaRepositories
public interface RouteDetailsRepo extends JpaRepository<RouteDetails, Integer> {

    @Query(value="select * from route_details r  inner join train_details t  on r.train_id=t.id where t.id=:trainId",nativeQuery = true)
    List<RouteDetails> findByTrainName(@Param(value = "trainId")int trainId);
}