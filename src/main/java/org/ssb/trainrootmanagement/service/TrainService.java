package org.ssb.trainrootmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssb.trainrootmanagement.model.Train;
import org.ssb.trainrootmanagement.repository.TrainRepo;

@Service
public class TrainService {

    @Autowired
    private TrainRepo trainRepo;

    public Train save(Train train) {
        return trainRepo.save(train);
    }

    public List<Train> getAll() {
        return trainRepo.findAll();
    }

    public List<Train> deleteById(int id) {
        trainRepo.deleteById(id);
        return getAll();

    }

    public Train updateById(int id, Train train) {
        Optional<Train> trainId = trainRepo.findById(id);

        if (trainId.isPresent()) {
            Train trainUpdate = trainId.get();
            trainUpdate.setTrainNumber(train.getTrainNumber());
            trainUpdate.setTrainName(train.getTrainName());

            return (trainRepo.save(trainUpdate));
        } else {
            return null;
        }

    }

}