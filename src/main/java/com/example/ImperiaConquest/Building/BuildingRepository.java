package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findByEmpireAndType(Empire empire, String type);
}
