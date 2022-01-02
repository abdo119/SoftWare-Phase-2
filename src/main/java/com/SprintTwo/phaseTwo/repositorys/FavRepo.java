package com.SprintTwo.phaseTwo.repositorys;

import com.SprintTwo.phaseTwo.models.FavouriteAreas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavRepo extends JpaRepository<FavouriteAreas, Long> {
    FavouriteAreas findBySource(String source);
}
