package org.ssb.trainrootmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssb.trainrootmanagement.model.RouteDetails;
import org.ssb.trainrootmanagement.repository.RouteDetailsRepo;

@Service
public class RouteDetailsService {

    @Autowired
    private RouteDetailsRepo routeDetailsRepo;

    public RouteDetails save(RouteDetails routeDetails) {
        return routeDetailsRepo.save(routeDetails);
    }

    public List<RouteDetails> getAll() {
        return routeDetailsRepo.findAll();
    }

    public List<RouteDetails> deleteById(int id) {
        routeDetailsRepo.deleteById(id);
        return getAll();
    }

}