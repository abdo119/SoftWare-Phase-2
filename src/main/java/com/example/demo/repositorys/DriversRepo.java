package com.example.demo.repositorys;

import com.example.demo.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversRepo extends JpaRepository<Driver,Long> {
    Driver findByEmail(String email);
}
