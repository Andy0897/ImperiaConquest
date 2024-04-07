package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Enums.BuildingTypes;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {
    @Autowired
    BuildingRepository buildingRepository;

    public BuildingService() {
    }

    public Optional<Building> getGarrisonBuildingsByEmpire(Empire empire) {
        return this.buildingRepository.findByEmpireAndType(empire, BuildingTypes.GARRISON.toString());
    }

    public Optional<Building> getQuartersBuildingsByEmpire(Empire empire) {
        return this.buildingRepository.findByEmpireAndType(empire, BuildingTypes.QUARTERS.toString());
    }

    public Optional<Building> getBarracksBuildingsByEmpire(Empire empire) {
        return this.buildingRepository.findByEmpireAndType(empire, BuildingTypes.BARRACKS.toString());
    }

    public BuildingRepository getBuildingRepository() {
        return this.buildingRepository;
    }
}
