package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Optional<Building> findByEmpireAndType(Empire empire, String type);
}
