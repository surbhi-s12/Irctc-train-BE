package com.project.irctc.irctc_trains.service.impl;

import com.project.irctc.irctc_trains.dto.TrainRouteDto;
import com.project.irctc.irctc_trains.entities.Station;
import com.project.irctc.irctc_trains.entities.Train;
import com.project.irctc.irctc_trains.entities.TrainRoute;
import com.project.irctc.irctc_trains.exception.ResourceNotFoundException;
import com.project.irctc.irctc_trains.respositories.StationRepository;
import com.project.irctc.irctc_trains.respositories.TrainRepository;
import com.project.irctc.irctc_trains.respositories.TrainRouteRepository;
import com.project.irctc.irctc_trains.service.TrainRouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainRouteServiceImpl implements TrainRouteService {
    public TrainRouteServiceImpl(TrainRouteRepository trainRouteRepository, TrainRepository trainRepository, StationRepository stationRepository) {
        this.trainRouteRepository = trainRouteRepository;
        this.trainRepository = trainRepository;
        this.stationRepository = stationRepository;
        this.modelMapper = new ModelMapper();
    }

    private final TrainRouteRepository trainRouteRepository;
    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;
    private ModelMapper modelMapper;

    @Override
    public TrainRouteDto addTrainRoute(TrainRouteDto dto) {
        Train train = trainRepository.findById(dto.getTrainDto().getId()).orElseThrow(() -> new ResourceNotFoundException("Train not found!!" + dto.getTrainDto().getId()));
        Station station = stationRepository.findById(dto.getStationDto().getId()).orElseThrow(() -> new ResourceNotFoundException("Station not found!!" + dto.getStationDto().getId()));

        //Convert Dto to Entity
        TrainRoute trainRoute = modelMapper.map(dto, TrainRoute.class);
        trainRoute.setTrain(train);
        trainRoute.setStation(station);
        // Save the TrainRoute entity
        TrainRoute savedTrainRoute = trainRouteRepository.save(trainRoute);
        // Convert saved entity back to DTO
        TrainRouteDto savedTrainRouteDto = modelMapper.map(savedTrainRoute, TrainRouteDto.class);


        return savedTrainRouteDto;
    }

    @Override
    public TrainRouteDto updateTrainRoute(Long trainId, TrainRouteDto trainRouteDto) {
        TrainRoute existingRoute = trainRouteRepository.findById(trainId).orElseThrow(() -> new ResourceNotFoundException("Train route not found with ID: " + trainId));
        Station station = this.stationRepository.findById(trainRouteDto.getStationDto().getId()).orElseThrow(() -> new ResourceNotFoundException("Station not found with ID: " + trainRouteDto.getStationDto().getId()));
        Train train = this.trainRepository.findById(trainRouteDto.getTrainDto().getId()).orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + trainRouteDto.getTrainDto().getId()));

        existingRoute.setStation(station);
        existingRoute.setTrain(train);
        existingRoute.setStationOrder(trainRouteDto.getStationOrder());
        existingRoute.setArrivalTime(trainRouteDto.getArrivalTime());
        existingRoute.setDepartureTime(trainRouteDto.getDepartureTime());
        existingRoute.setHaltMinutes(trainRouteDto.getHaltMinutes());
        existingRoute.setDistanceFromSource(trainRouteDto.getDistanceFromSource());
        // Save the updated route
        TrainRoute updatedRoute = trainRouteRepository.save(existingRoute);
        // Convert updated entity back to DTO
        TrainRouteDto updatedRouteDto = modelMapper.map(updatedRoute, TrainRouteDto.class);
        return updatedRouteDto;
    }

    @Override
    public List<TrainRouteDto> getRouteByTrainId(Long trainId) {
        Train train = this.trainRepository.findById(trainId).orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + trainId));
        List<TrainRoute> trainRoutes = this.trainRouteRepository.findAllByTrainId(trainId);
        List<TrainRouteDto> trainRouteDtos = trainRoutes.stream().map(trainRoute -> modelMapper.map(trainRoutes, TrainRouteDto.class)).toList();
        return trainRouteDtos;
    }

    @Override
    public void deleteTrainRoute(Long id) {
        TrainRoute existingRoute = trainRouteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Train route not found with ID: " + id));
        trainRouteRepository.delete(existingRoute);

    }
}
