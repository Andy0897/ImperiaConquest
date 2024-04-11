package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Enums.BuildingTypes;
import com.example.ImperiaConquest.Unit.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class BuildingService {
    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    UnitRepository unitRepository;

    public BuildingService() {
    }

    public BuildingRepository getBuildingRepository() {
        return this.buildingRepository;
    }

    public UnitRepository getUnitRepository() {
        return this.unitRepository;
    }

    public Building getEmpireBuildingByType(Empire empire, String type) {
        return this.buildingRepository.findByEmpireAndType(empire, type).orElseGet(Building::new);
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

    public HashMap<String, Integer> getGarrisonBuildingCost(Empire empire) {
        Integer level = this.buildingRepository.findLevelOrDefaultByEmpireAndType(empire, BuildingTypes.GARRISON.toString(), 0);
        return (new BuildingCostCalculator(level, BuildingTypes.GARRISON.toString())).calculate();
    }

    public HashMap<String, Integer> getBarracksBuildingCost(Empire empire) {
        Integer level = this.buildingRepository.findLevelOrDefaultByEmpireAndType(empire, BuildingTypes.BARRACKS.toString(), 0);
        return (new BuildingCostCalculator(level, BuildingTypes.BARRACKS.toString())).calculate();
    }

    public HashMap<String, Integer> getQuartersBuildingCost(Empire empire) {
        Integer level = this.buildingRepository.findLevelOrDefaultByEmpireAndType(empire, BuildingTypes.QUARTERS.toString(), 0);
        return (new BuildingCostCalculator(level, BuildingTypes.QUARTERS.toString())).calculate();
    }
}
