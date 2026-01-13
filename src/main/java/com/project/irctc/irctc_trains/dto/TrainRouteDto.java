package com.project.irctc.irctc_trains.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainRouteDto {
    private Long id;
    private TrainDto trainDto;
    private StationDto stationDto;
    private Integer stationOrder;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer haltMinutes;
    private Integer distanceFromSource;
}
