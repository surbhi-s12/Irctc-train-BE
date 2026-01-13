package com.project.irctc.irctc_trains.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "train_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private long size;
    private LocalDateTime uploadTime = LocalDateTime.now();

    @OneToOne(mappedBy = "train_image")
    private Train train;

}
