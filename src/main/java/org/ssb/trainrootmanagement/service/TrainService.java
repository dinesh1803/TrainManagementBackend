package org.ssb.trainrootmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssb.trainrootmanagement.exception.AlreadyExistException;
import org.ssb.trainrootmanagement.model.Trains;
import org.ssb.trainrootmanagement.repository.TrainRepo;

@Service
public class TrainService {

    @Autowired
    private TrainRepo trainRepo;

    public Trains save(Trains train) {
        return trainRepo.save(train);
    }

    public List<Trains> getAll() {
        return trainRepo.findAll();
    }

    public List<Trains> deleteById(int id) {
        trainRepo.deleteById(id);
        return getAll();

    }

    public Trains updateById(int id, Trains train) {
        Optional<Trains> trainId = trainRepo.findById(id);

        if (trainId.isPresent()) {
            Trains trainUpdate = trainId.get();
            trainUpdate.setTrainNumber(train.getTrainNumber());
            trainUpdate.setTrainName(train.getTrainName());

            return (trainRepo.save(trainUpdate));
        } else {
            return null;
        }

    }

    public List<Trains> getBySourceAndDest(String source, String dest) throws AlreadyExistException {
        if(source.equals(dest)){
            throw new AlreadyExistException("select different destination");
        }
        return trainRepo.findBySourceAndDest(source, dest);
    }



}