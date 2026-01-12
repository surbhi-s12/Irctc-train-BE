package com.project.irctc.irctc_trains.service.impl;

import com.project.irctc.irctc_trains.dto.PagedResponse;
import com.project.irctc.irctc_trains.dto.StationDto;
import com.project.irctc.irctc_trains.entities.Station;
import com.project.irctc.irctc_trains.exception.ResourceNotFoundException;
import com.project.irctc.irctc_trains.respositories.StationRepository;
import com.project.irctc.irctc_trains.service.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;
    private final ModelMapper modelMapper;

    public StationServiceImpl(StationRepository stationRepository, ModelMapper modelMapper) {
        this.stationRepository = stationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StationDto createStation(StationDto stationDto) {
        Station station = modelMapper.map(stationDto, Station.class);
        Station savedStation = stationRepository.save(station);
        return modelMapper.map(savedStation, StationDto.class);
    }

    @Override
    public PagedResponse<StationDto> getAllStation(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.trim().equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Station> stations = stationRepository.findAll(pageable);
        Page<StationDto> stationDto = stations.map(station -> modelMapper.map(station, StationDto.class));
        return PagedResponse.fromPage(stationDto);
    }

    @Override
    public StationDto getSingleStation(Long id) {
        Station station = stationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("station not found with given id !"));
        return modelMapper.map(station, StationDto.class);
    }

    @Override
    public StationDto updateStation(Long id, StationDto stationDto) {
        Station station = stationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("station not found with given id !"));
        station.setStationName(stationDto.getStationName());
        station.setStationState(stationDto.getStationState());
        station.setStationCity(stationDto.getStationCity());
        station.setCode(stationDto.getCode());
        Station updatedStation = stationRepository.save(station);
        return modelMapper.map(updatedStation, StationDto.class);
    }

    @Override
    public void deleteStation(Long id) {
        Station station = stationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("station not found with given id !"));
        stationRepository.delete(station);
    }
}
