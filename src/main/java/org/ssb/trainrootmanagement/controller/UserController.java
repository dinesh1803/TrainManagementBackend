package org.ssb.trainrootmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ssb.trainrootmanagement.model.LoginDetails;
import org.ssb.trainrootmanagement.model.Route;
import org.ssb.trainrootmanagement.model.RouteDetails;
import org.ssb.trainrootmanagement.model.Station;
import org.ssb.trainrootmanagement.model.Train;
import org.ssb.trainrootmanagement.service.RouteDetailsService;
import org.ssb.trainrootmanagement.service.RouteService;
import org.ssb.trainrootmanagement.service.StationService;
import org.ssb.trainrootmanagement.service.TrainService;
import org.ssb.trainrootmanagement.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private TrainService trainService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private StationService stationService;
    @Autowired
    private RouteDetailsService routeDetailsService;
    @Autowired
    private UserService userService;


    @PostMapping(value="/post")
    public LoginDetails saveUser(@RequestBody LoginDetails user) {
        return userService.save(user);
    }

    @PutMapping(value = "/update")
    public Optional<LoginDetails> updateByTrainId(@RequestBody LoginDetails user) {
        return userService.updateById(user);
    }
    
//train details
    @GetMapping(value = "/traindetails-get")
    public List<Train> getAllDetails() {
        return trainService.getAll();
    }

 //routes   
    @GetMapping(value = "/routes/get")
    public List<Route> getAllRoutes() {
        return routeService.getAll();
    }
    
    @GetMapping(value = "/routes/source")
    public List<Route> getBySource(@RequestParam ("source") String source , @RequestParam ("source") String destination) {
        return routeService.getBySource(source,destination);
    }

//station    
    @GetMapping(value = "/station")
    public List<Station> getAllStation() {
        return stationService.getAll();
    }

    @GetMapping(value = "/routedetails")
    public List<RouteDetails> getAllRouteDetails() {
        return routeDetailsService.getAll();
    }
}