package com.project.irctc.irctc_trains.respositories;

import com.project.irctc.irctc_trains.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station,Long> {
}
