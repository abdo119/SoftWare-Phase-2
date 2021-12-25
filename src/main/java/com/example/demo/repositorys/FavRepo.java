package com.example.demo.repositorys;

import com.example.demo.models.Admin;
import com.example.demo.models.FavouriteAreas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavRepo extends JpaRepository<FavouriteAreas,Long> {
    FavouriteAreas findBySource(String source);
}
