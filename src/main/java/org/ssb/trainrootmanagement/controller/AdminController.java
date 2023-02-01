package org.ssb.trainrootmanagement.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ssb.trainrootmanagement.model.Route;
import org.ssb.trainrootmanagement.model.RouteDetails;
import org.ssb.trainrootmanagement.model.Station;
import org.ssb.trainrootmanagement.model.Trains;
import org.ssb.trainrootmanagement.Dto.TrainCountDto;
import org.ssb.trainrootmanagement.exception.AlreadyExistException;
import org.ssb.trainrootmanagement.model.LoginDetails;
import org.ssb.trainrootmanagement.model.TrainStatus;
import org.ssb.trainrootmanagement.service.UserService;
import org.ssb.trainrootmanagement.service.RouteDetailsService;
import org.ssb.trainrootmanagement.service.RouteService;
import org.ssb.trainrootmanagement.service.StationService;
import org.ssb.trainrootmanagement.service.StatusService;
import org.ssb.trainrootmanagement.service.TrainService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private TrainService trainService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private StationService stationService;
    @Autowired
    private RouteDetailsService routeDetailsService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserService userService;

    // By Train
    @PostMapping(value = "/traindetails/post")
    public Trains saveTrains(@RequestBody Trains train) {

        return trainService.save(train);
    }

    @GetMapping(value = "/traindetails/get")
    public List<Trains> getAllDetails() {
        return trainService.getAll();
    }

    @DeleteMapping(value = "/traindetails/delete/{id}")
    public List<Trains> deleteById(@PathVariable("id") int id) {
        return trainService.deleteById(id);
    }

    @PutMapping(value = "/traindetails/update/{id}")
    public Trains updateByTrainId(@PathVariable("id") int id, @RequestBody Trains train) {
        return trainService.updateById(id, train);
    }

    @GetMapping(value = "/traindetails/getBySourceAndDest")
    public List<Trains> getBySourceAndDest(@RequestParam("source")String source,@RequestParam("destination")String dest ) {
        return trainService.getBySourceAndDest(source,dest);
    }

    // By Route
    @PostMapping(value = "/route/post")
    public Route saveRoutes(@RequestBody Route route) throws AlreadyExistException {

        return routeService.save(route);
    }

    @GetMapping(value = "/route/get")
    public List<Route> getAllRoutes() {
        return routeService.getAll();
    }

    @DeleteMapping(value = "/route/delete/{id}")
    public List<Route> deleteRoute(@PathVariable("id") int id) {
        return routeService.deleteById(id);
    }

    // up coming api are station details

    @PostMapping(value = "/station/post")
    public Station saveStation(@RequestBody Station station) {

        return stationService.save(station);
    }

    @GetMapping(value = "/station/get")
    public List<Station> getAllStation() {
        return stationService.getAll();
    }

    @DeleteMapping(value = "/station/delete/{id}")
    public List<Station> deleteStation(@PathVariable("id") int id) {
        return stationService.deleteById(id);
    }

    // routeDetails

    @PostMapping(value = "/route-details/post")
    public RouteDetails saveRouteDetails(@RequestBody RouteDetails routeDetails) {

        return routeDetailsService.save(routeDetails);
    }

    @GetMapping(value = "/route-details/get")
    public List<RouteDetails> getAllRouteDetails() {
        return routeDetailsService.getAll();
    }

    @DeleteMapping(value = "/route-details/delete/{id}")
    public List<RouteDetails> deleteRouteDetails(@PathVariable("id") int id) {
        return routeDetailsService.deleteById(id);
    }

    @GetMapping(value = "/route-details/gettrain")
    public List<RouteDetails> getByTrainName(@RequestParam("trainName") int trainName) {
        return routeDetailsService.getByTrainName(trainName);
    }

    @GetMapping(value = "/route-details/gettrainid")
    public Set<Trains> getByTrainId(@RequestParam("source") int source,@RequestParam("dest") int dest) {
        return routeDetailsService.getByTrainId(source,dest);
    }
    

    // train status
    @PostMapping(value = "/trainstatus/post")
    public TrainStatus saveTrainStatus(@RequestBody TrainStatus trainStatus) {

        return statusService.save(trainStatus);
    }

    @GetMapping(value = "/trainstatus/get")
    public List<TrainStatus> getAllStatus() {
        return statusService.getAll();
    }

    @DeleteMapping(value = "/trainStatus/delete/{id}")
    public List<TrainStatus> deleteStatus(@PathVariable("id") int id) {
        return statusService.deleteById(id);
    }

  


    // By role

    @PostMapping(value = "/post")
    public LoginDetails saveUser(@RequestBody LoginDetails user) {
        return userService.save(user);
    }

    @GetMapping(value = "/get")
    public List<LoginDetails> getAllUser() {
        return userService.getAll();
    }

    @DeleteMapping(value = "/delete")
    public void deleteUser(@RequestParam("id") int id) {
        userService.deleteById(id);
    }

}