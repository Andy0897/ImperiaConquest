package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Enums.BuildingTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    public List<Building> getGarrisonBuildingsByEmpire(Empire empire) {
        return buildingRepository.findByEmpireAndType(empire, BuildingTypes.GARRISON.toString());
    }
}
