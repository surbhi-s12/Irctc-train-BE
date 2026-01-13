package com.project.irctc.irctc_trains.respositories;

import com.project.irctc.irctc_trains.entities.TrainImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainImageRepository extends JpaRepository<TrainImage, Long> {
}
