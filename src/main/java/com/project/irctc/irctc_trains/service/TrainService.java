package com.project.irctc.irctc_trains.service;

import com.project.irctc.irctc_trains.dto.TrainDto;

import java.util.List;

public interface TrainService {
     TrainDto createTrain(TrainDto trainDTO);
     List<TrainDto> getAllTrains();
     TrainDto getSingleTrain(Long id);
     TrainDto updateTrain(Long id, TrainDto trainDto);
     void deleteTrain(Long id);
}
