package org.ssb.trainrootmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssb.trainrootmanagement.exception.AlreadyExistException;
import org.ssb.trainrootmanagement.model.Route;
import org.ssb.trainrootmanagement.repository.RouteRepo;

@Service
public class RouteService {
    @Autowired
    private RouteRepo routeRepo;

    public Route save(Route routes) throws AlreadyExistException {

        if (routes.source.equals(routes.destination)) {
            throw new AlreadyExistException("this already");
        }
        return routeRepo.save(routes);
    }

    public List<Route> getAll() {
        return routeRepo.findAll();
    }

    public List<Route> getBySource(String source, String destination) {
        return routeRepo.findBySourceAndDestination(source, destination);
    }

    public List<Route> deleteById(int id) {
        routeRepo.deleteById(id);
        return getAll();
    }

}