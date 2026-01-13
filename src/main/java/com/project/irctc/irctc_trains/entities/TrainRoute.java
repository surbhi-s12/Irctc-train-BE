package com.project.irctc.irctc_trains.entities;

import com.project.irctc.irctc_trains.dto.StationDto;
import com.project.irctc.irctc_trains.dto.TrainDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "train_route")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
    private Integer stationOrder;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer haltMinutes;
    private Integer distanceFromSource;
}
