package com.example.ImperiaConquest.Building.Structures;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Enums.BuildingTypes;
import com.example.ImperiaConquest.Enums.ResourceTypes;
import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.BuildingStructureInterface;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Structures.Archer;
import com.example.ImperiaConquest.Unit.Structures.Knight;
import com.example.ImperiaConquest.Unit.Unit;

import java.util.LinkedHashMap;
import java.util.Map;

public class Garrison extends BuildingItem implements BuildingStructureInterface {

    public Garrison(Empire empire, BuildingService buildingService) {
        super(empire, buildingService);
    }

     @Override
    public void setBuilding() {
        this.building = buildingService.getGarrisonBuildingsByEmpire(empire).orElseGet(Building::new);
    }

    @Override
    public String getName() {
        return "Garrison";
    }

    @Override
    public String getType() {
        return BuildingTypes.GARRISON.name();
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
        return this.buildingService.getGarrisonBuildingCost(this.empire).get(ResourceTypes.GOLD.name());
    }

    @Override
    public Integer getBuildingIronCost() {
        return this.buildingService.getGarrisonBuildingCost(this.empire).get(ResourceTypes.IRON.name());
    }

    @Override
    public Integer getBuildingWoodCost() {
        return this.buildingService.getGarrisonBuildingCost(this.empire).get(ResourceTypes.WOOD.name());
    }

    @Override
    public Map<String, UnitInterface> getUnits() {
        Map<String, UnitInterface> units = new LinkedHashMap<>();

        units.put(UnitTypes.ARCHER.name(), new Archer(this.getUnit(UnitTypes.ARCHER.name())));
        units.put(UnitTypes.KNIGHT.name(), new Knight(this.getUnit(UnitTypes.KNIGHT.name())));
        return units;
    }
}
