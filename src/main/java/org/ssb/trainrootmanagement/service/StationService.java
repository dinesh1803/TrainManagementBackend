package org.ssb.trainrootmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssb.trainrootmanagement.model.Station;
import org.ssb.trainrootmanagement.repository.StationRepo;

@Service
public class StationService {
    @Autowired
    public StationRepo stationRepo;

    public List<Station> getAll() {
        return stationRepo.findAll();
    }

    public Station save(Station station) {
        station.setStationName(station.getStationName().toUpperCase());
        station.setStationCode(station.getStationCode().toUpperCase());
        return stationRepo.save(station);
    }

    public List<Station> deleteById(int id) {
        stationRepo.deleteById(id);
        return getAll();
    }

    
}