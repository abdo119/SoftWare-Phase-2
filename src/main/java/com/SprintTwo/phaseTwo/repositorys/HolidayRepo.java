package com.SprintTwo.phaseTwo.repositorys;

import com.SprintTwo.phaseTwo.models.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepo extends JpaRepository<Holidays,Long> {
}
