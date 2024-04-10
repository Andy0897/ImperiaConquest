package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import com.example.ImperiaConquest.Enums.BuildingTypes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class BuildingsDTO {

    public Empire empire;

    @Autowired
    EmpireService empireService;
    BuildingService buildingService;

    @Autowired
    public BuildingsDTO(Empire empire, EmpireService empireService) {
        this.empire = empire;
        this.empireService = empireService;
    }

    public HashMap<String, Building> getBuildings() {
        HashMap<String, Building> buildings = new HashMap<>();
        buildings.put(BuildingTypes.GARRISON.name(), empireService.getGarrisonBuildings(empire));
        buildings.put(BuildingTypes.BARRACKS.name(), empireService.getBarracksBuildings(empire));
        buildings.put(BuildingTypes.QUARTERS.name(), empireService.getQuartersBuildings(empire));
        return buildings;
    }

    ;
}
