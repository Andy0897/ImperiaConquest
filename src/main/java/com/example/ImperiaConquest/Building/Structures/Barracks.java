package com.example.ImperiaConquest.Building.Structures;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Enums.BuildingTypes;
import com.example.ImperiaConquest.Enums.ResourceTypes;
import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.BuildingStructureInterface;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Structures.Ninja;
import com.example.ImperiaConquest.Unit.Structures.Viking;

import java.util.LinkedHashMap;
import java.util.Map;

public class Barracks extends BuildingItem implements BuildingStructureInterface {

    public Barracks(Empire empire, BuildingService buildingService) {
        super(empire, buildingService);
    }
    
    @Override
    public String getName() {
        return "Barracks";
    }

    @Override
    public String getType() {
        return BuildingTypes.BARRACKS.name();
    }

    @Override
    public Building getBuilding() {
        return this.building;
    }

    @Override
    public void setBuilding() {
        this.building = buildingService.getBarracksBuildingsByEmpire(empire).orElseGet(Building::new);
    }

    @Override
    public Boolean exists() {
        return this.building.getId() != null;
    }

    @Override
    public Integer getBuildingGoldCost() {
        return this.buildingService.getBarracksBuildingCost(this.empire).get(ResourceTypes.GOLD.name());
    }

    @Override
    public Integer getBuildingIronCost() {
        return this.buildingService.getBarracksBuildingCost(this.empire).get(ResourceTypes.IRON.name());
    }

    @Override
    public Integer getBuildingWoodCost() {
        return this.buildingService.getBarracksBuildingCost(this.empire).get(ResourceTypes.WOOD.name());
    }

    @Override
    public Map<String, UnitInterface> getUnits() {
        Map<String, UnitInterface> units = new LinkedHashMap<>();

        units.put(UnitTypes.NINJA.name(), new Ninja(this.getUnit(UnitTypes.NINJA.name())));
        units.put(UnitTypes.VIKING.name(), new Viking(this.getUnit(UnitTypes.VIKING.name())));
        return units;
    }
}
