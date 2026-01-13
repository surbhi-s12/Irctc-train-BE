package com.project.irctc.irctc_trains.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "train")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainName;
    private String trainNumber;
    private Integer totalDistance;

    @ManyToOne
    @JoinColumn(name = "source_station_id")
    private Station sourceStation;

    @ManyToOne
    @JoinColumn(name= "destination_station_id")
    private Station destinationStation;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private TrainImage trainImage;


}
