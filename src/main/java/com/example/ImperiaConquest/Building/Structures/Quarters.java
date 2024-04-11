package com.example.ImperiaConquest.Building.Structures;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Enums.BuildingTypes;
import com.example.ImperiaConquest.Enums.ResourceTypes;
import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.BuildingStructureInterface;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Structures.Catapult;
import com.example.ImperiaConquest.Unit.Structures.Pirate;

import java.util.LinkedHashMap;
import java.util.Map;

public class Quarters extends BuildingItem implements BuildingStructureInterface {

    public Quarters(Empire empire, BuildingService buildingService) {
        super(empire, buildingService);
    }

    @Override
    public void setBuilding() {
        this.building = buildingService.getQuartersBuildingsByEmpire(empire).orElseGet(Building::new);
    }

    @Override
    public String getName() {
        return "Quarters";
    }

    @Override
    public String getType() {
        return BuildingTypes.QUARTERS.name();
    }

    @Override
    public Building getBuilding() {
        return this.building;
    }

    @Override
    public Boolean exists() {
        return this.building.getId() != null;
    }

    @Override
    public Integer getBuildingGoldCost() {
        return this.buildingService.getQuartersBuildingCost(this.empire).get(ResourceTypes.GOLD.name());
    }

    @Override
    public Integer getBuildingIronCost() {
        return this.buildingService.getQuartersBuildingCost(this.empire).get(ResourceTypes.IRON.name());
    }

    @Override
    public Integer getBuildingWoodCost() {
        return this.buildingService.getQuartersBuildingCost(this.empire).get(ResourceTypes.WOOD.name());
    }

    @Override
    public Map<String, UnitInterface> getUnits() {
        Map<String, UnitInterface> units = new LinkedHashMap<>();

        units.put(UnitTypes.CATAPULT.name(), new Catapult(this.getUnit(UnitTypes.CATAPULT.name())));
        units.put(UnitTypes.PIRATE.name(), new Pirate(this.getUnit(UnitTypes.PIRATE.name())));
        return units;
    }
}
