package com.SprintTwo.phaseTwo.repositorys;

import com.SprintTwo.phaseTwo.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversRepo extends JpaRepository<Driver, Long> {
    Driver findByEmail(String email);
}
