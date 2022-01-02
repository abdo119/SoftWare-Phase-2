package com.SprintTwo.phaseTwo.repositorys;

import com.SprintTwo.phaseTwo.models.Ride;
import com.SprintTwo.phaseTwo.models.SuccessfulRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessfulRideRepo extends JpaRepository<SuccessfulRide, Long> {
    SuccessfulRide findSuccessfulRideByRide(Ride ride);
}
