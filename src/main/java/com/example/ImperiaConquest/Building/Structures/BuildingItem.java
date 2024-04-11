package com.example.ImperiaConquest.Building.Structures;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Unit.Unit;

public class BuildingItem {

    BuildingService buildingService;
    Empire empire;
    Building building;

    public BuildingItem(Empire empire, BuildingService buildingService) {
        this.empire = empire;
        this.buildingService = buildingService;

        this.setBuilding();
    }

     protected Unit getUnit(String type) {
        return this.buildingService.getUnitRepository().findByEmpireAndType(this.empire, type).orElseGet(Unit::new);
    }

    protected void setBuilding() {

    }
}
