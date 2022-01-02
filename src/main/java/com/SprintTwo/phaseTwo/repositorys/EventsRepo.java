package com.SprintTwo.phaseTwo.repositorys;

import com.SprintTwo.phaseTwo.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepo extends JpaRepository<Events, Long> {
}
