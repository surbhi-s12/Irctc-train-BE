package com.project.irctc.irctc_trains.controllers.admin;

import com.project.irctc.irctc_trains.dto.TrainDto;
import com.project.irctc.irctc_trains.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/train")
public class TrainController {
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    private final TrainService trainService;

    @PostMapping
    ResponseEntity<TrainDto> createTrain(@Valid @RequestBody TrainDto trainDto) {
        TrainDto train = trainService.createTrain(trainDto);
        return new ResponseEntity<>(train, HttpStatus.CREATED);

    }

    @GetMapping
    public List<TrainDto> getAllTrains() {
        return trainService.getAllTrains();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainDto> getSingleTrain(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(trainService.getSingleTrain(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainDto> updateTrain(
            @PathVariable("id") Long id,
            @RequestBody TrainDto trainDTO
    ) {
        return new ResponseEntity<>(trainService.updateTrain(id, trainDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable("id") Long id) {
        trainService.deleteTrain(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
