package org.ssb.trainrootmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.ssb.trainrootmanagement.model.RouteDetails;


public interface RouteDetailsRepo extends JpaRepository<RouteDetails, Integer> {

}