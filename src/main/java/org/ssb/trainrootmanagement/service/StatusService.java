package org.ssb.trainrootmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssb.trainrootmanagement.model.TrainStatus;
import org.ssb.trainrootmanagement.repository.StatusRepo;

@Service
public class StatusService {
    @Autowired
    private StatusRepo statusRepo;

    public List<TrainStatus> getAll() {
        return statusRepo.findAll();
    }

    public TrainStatus save(TrainStatus trainStatus) {
        return statusRepo.save(trainStatus);
    }

    public List<TrainStatus> deleteById(int id) {
     statusRepo.deleteById(id);
     return getAll();
    }

}