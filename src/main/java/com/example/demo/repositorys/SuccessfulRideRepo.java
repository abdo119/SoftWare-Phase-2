package com.example.demo.repositorys;

import com.example.demo.models.SuccessfulRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessfulRideRepo extends JpaRepository<SuccessfulRide, Long> {
}
