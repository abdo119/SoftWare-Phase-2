package com.example.demo.repositorys;

import com.example.demo.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RidesRepo extends JpaRepository<Ride,Long> {

}
