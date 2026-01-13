package com.project.irctc.irctc_trains.dto;

import com.project.irctc.irctc_trains.entities.TrainImage;
import org.springframework.core.io.Resource;

public record TrainImageDataWithResource(
        TrainImage trainImage,
        Resource resource) {

}
