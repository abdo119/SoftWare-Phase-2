package com.example.demo.repositorys;

import com.example.demo.models.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepo extends JpaRepository<Holidays,Long> {
}