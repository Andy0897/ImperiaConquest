package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Optional<Building> findByEmpireAndType(Empire empire, String type);

    @Query("SELECT b.level FROM Building b WHERE b.empire = :empire AND b.type = :type")
    Optional<Integer> findLevelByEmpireAndType(@Param("empire") Empire empire, @Param("type") String type);

    default int findLevelOrDefaultByEmpireAndType(Empire empire, String type, int defaultValue) {
        return findLevelByEmpireAndType(empire, type).orElse(defaultValue);
    }
}
