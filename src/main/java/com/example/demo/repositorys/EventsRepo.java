package com.example.demo.repositorys;

import com.example.demo.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepo extends JpaRepository<Events, Long> {
}
