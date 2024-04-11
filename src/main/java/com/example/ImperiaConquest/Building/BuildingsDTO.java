package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Building.Structures.Barracks;
import com.example.ImperiaConquest.Building.Structures.Garrison;
import com.example.ImperiaConquest.Building.Structures.Quarters;
import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import com.example.ImperiaConquest.Enums.BuildingTypes;
import com.example.ImperiaConquest.Interfaces.BuildingStructureInterface;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BuildingsDTO {

    public Empire empire;
    EmpireService empireService;
    BuildingService buildingService;

    public BuildingsDTO(Empire empire, EmpireService empireService, BuildingService buildingService) {
        this.empire = empire;
        this.empireService = empireService;
        this.buildingService = buildingService;
    }

    public HashMap<String, Building> getBuildings() {
        HashMap<String, Building> buildings = new HashMap<>();
        buildings.put(BuildingTypes.GARRISON.name(), empireService.getGarrisonBuildings(empire));
        buildings.put(BuildingTypes.BARRACKS.name(), empireService.getBarracksBuildings(empire));
        buildings.put(BuildingTypes.QUARTERS.name(), empireService.getQuartersBuildings(empire));
        return buildings;
    }

    public Map<String, BuildingStructureInterface> getBuildingStructures() {
        Map<String, BuildingStructureInterface> buildingStructures = new LinkedHashMap<>();
        buildingStructures.put(BuildingTypes.GARRISON.name(), new Garrison(empire, buildingService));
        buildingStructures.put(BuildingTypes.QUARTERS.name(), new Quarters(empire, buildingService));
        buildingStructures.put(BuildingTypes.BARRACKS.name(), new Barracks(empire, buildingService));
        return buildingStructures;
    }
}
