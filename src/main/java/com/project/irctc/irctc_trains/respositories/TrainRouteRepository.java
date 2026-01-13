package com.project.irctc.irctc_trains.respositories;

import com.project.irctc.irctc_trains.entities.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRouteRepository extends JpaRepository<TrainRoute, Long> {
    @Query("SELECT tr FROM TrainRoute tr Where tr.train.id =?1 order by tr.stationOrder")
    List<TrainRoute> findAllByTrainId(Long trainId);
}
