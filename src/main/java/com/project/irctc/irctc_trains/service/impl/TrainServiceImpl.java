package com.project.irctc.irctc_trains.service.impl;

import com.project.irctc.irctc_trains.dto.TrainDto;
import com.project.irctc.irctc_trains.entities.Station;
import com.project.irctc.irctc_trains.entities.Train;
import com.project.irctc.irctc_trains.exception.ResourceNotFoundException;
import com.project.irctc.irctc_trains.respositories.StationRepository;
import com.project.irctc.irctc_trains.respositories.TrainRepository;
import com.project.irctc.irctc_trains.service.TrainService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {
    private final StationRepository stationRepository;
    private final TrainRepository trainRepository;
    private final ModelMapper modelMapper;

    public TrainServiceImpl(StationRepository stationRepository, TrainRepository trainRepository, ModelMapper modelMapper) {
        this.stationRepository = stationRepository;
        this.trainRepository = trainRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TrainDto createTrain(TrainDto trainDTO) {
        Station sourceStation = stationRepository.findById(trainDTO.getSourceStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Source station not found with id: " + trainDTO.getSourceStation().getId()));
        Station destinationStation = stationRepository.findById(trainDTO.getDestinationStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Destination station not found with id: " + trainDTO.getDestinationStation().getId()));
        Train train = modelMapper.map(trainDTO, Train.class);
        train.setSourceStation(sourceStation);
        train.setDestinationStation(destinationStation);
        Train savedTrain = trainRepository.save(train);

        return modelMapper.map(savedTrain, TrainDto.class);
    }

    @Override
    public List<TrainDto> getAllTrains() {
        List<Train> trains = trainRepository.findAll();
        return trains.stream().map(train -> modelMapper.map(train, TrainDto.class)).toList();
    }

    @Override
    public TrainDto getSingleTrain(Long id) {
        Train train = trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + id));
        return modelMapper.map(train, TrainDto.class);
    }

    @Override
    public TrainDto updateTrain(Long id, TrainDto trainDto) {
        Train existingTrain = trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + id));
        existingTrain.setTrainNumber(trainDto.getNumber());
        existingTrain.setTrainName(trainDto.getName());
        existingTrain.setTotalDistance(trainDto.getTotalDistance());
        Station sourceStation = stationRepository.findById(trainDto.getSourceStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Source station not found with id: " + trainDto.getSourceStation().getId()));
        Station destinationStation = stationRepository.findById(trainDto.getDestinationStation().getId()).orElseThrow(() -> new ResourceNotFoundException("Destination station not found with id: " + trainDto.getDestinationStation().getId()));
        existingTrain.setSourceStation(sourceStation);
        existingTrain.setDestinationStation(destinationStation);
        Train updatedTrain = trainRepository.save(existingTrain);
        return modelMapper.map(updatedTrain, TrainDto.class);

    }

    @Override
    public void deleteTrain(Long id) {
        Train train = trainRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + id));
        trainRepository.delete(train);

    }
}
