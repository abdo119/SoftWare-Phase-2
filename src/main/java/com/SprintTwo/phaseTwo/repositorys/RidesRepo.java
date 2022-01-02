package com.SprintTwo.phaseTwo.repositorys;

import com.SprintTwo.phaseTwo.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RidesRepo extends JpaRepository<Ride, Long> {

}
