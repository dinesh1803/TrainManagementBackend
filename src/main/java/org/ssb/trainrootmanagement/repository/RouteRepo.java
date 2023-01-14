package org.ssb.trainrootmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.ssb.trainrootmanagement.model.Route;



public interface RouteRepo extends JpaRepository<Route, Integer> {

    List<Route> findBySourceAndDestination(String source,String destination);
}