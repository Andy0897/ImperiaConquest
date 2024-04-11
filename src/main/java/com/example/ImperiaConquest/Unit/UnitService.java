package com.example.ImperiaConquest.Unit;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Enums.UnitTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UnitService {


    @Autowired
    UnitRepository unitRepository;
    HashMap<String, Integer> unitsCountGeneratedPerHour;

    public UnitService() {
        this.setUnitsCountGeneratedPerHour();
    }

    public UnitRepository getUnitRepository() {
        return this.unitRepository;
    }

    public void setUnitsCountGeneratedPerHour() {
        this.unitsCountGeneratedPerHour = new HashMap<String, Integer>();

        unitsCountGeneratedPerHour.put(UnitTypes.ARCHER.name(), 10);
        unitsCountGeneratedPerHour.put(UnitTypes.KNIGHT.name(), 8);
        unitsCountGeneratedPerHour.put(UnitTypes.CATAPULT.name(), 7);
        unitsCountGeneratedPerHour.put(UnitTypes.PIRATE.name(), 7);
        unitsCountGeneratedPerHour.put(UnitTypes.NINJA.name(), 3);
        unitsCountGeneratedPerHour.put(UnitTypes.VIKING.name(), 2);
    }

    public HashMap<String, Integer> getUnitsCountGeneratedPerHour() {
        return this.unitsCountGeneratedPerHour;
    }

    public Integer generatedUnitsPerHour(String unitName) {
        return this.unitsCountGeneratedPerHour.get(unitName);
    }

    public Integer calculateUnitsPerHour(Building building, String unitName) {

        if (building.getLevel() == 0) {
            return this.generatedUnitsPerHour(unitName);
        }

        return (int) (Math.ceil((double) building.getLevel() / 10) + generatedUnitsPerHour(unitName));
    }
}
