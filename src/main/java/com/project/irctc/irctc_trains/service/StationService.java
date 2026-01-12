package com.project.irctc.irctc_trains.service;

import com.project.irctc.irctc_trains.dto.PagedResponse;
import com.project.irctc.irctc_trains.dto.StationDto;


public interface StationService {
    StationDto createStation(StationDto stationDto);

    PagedResponse<StationDto> getAllStation(int page, int size, String sortBy, String sortDir);

    StationDto getSingleStation(Long id);

    StationDto updateStation(Long id, StationDto stationDto);

    void deleteStation(Long id);

}
