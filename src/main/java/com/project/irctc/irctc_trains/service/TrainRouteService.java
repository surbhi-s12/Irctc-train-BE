package com.project.irctc.irctc_trains.service;

import com.project.irctc.irctc_trains.dto.TrainRouteDto;

import java.util.List;

public interface TrainRouteService {
    TrainRouteDto addTrainRoute(TrainRouteDto dto);
    TrainRouteDto updateTrainRoute(Long trainId, TrainRouteDto trainRouteDto);
    List<TrainRouteDto> getRouteByTrainId(Long trainId);
    void deleteTrainRoute(Long id);
}
