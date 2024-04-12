package com.example.ImperiaConquest.Unit;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Structures.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Integer generatedUnitsPerHour(String unitName) {
        return this.unitsCountGeneratedPerHour.get(unitName);
    }

    public Integer calculateUnitsPerHour(Building building, String unitName) {

        if (building.getLevel() == 0) {
            return this.generatedUnitsPerHour(unitName);
        }

        return (int) (Math.ceil((double) building.getLevel() / 10) + generatedUnitsPerHour(unitName) + building.getLevel());
    }

    public List<UnitItem> getUnitsByEmpire(Empire empire) {
        List<Unit> units = this.unitRepository.findByEmpire(empire);
        return units.stream().map(this::getUnitInstanceByType).toList();
    }

    public UnitItem getUnitInstanceByType(Unit unit) {
        UnitTypes unitType = UnitTypes.valueOf(unit.getType().toUpperCase());

        return switch (unitType) {
            case ARCHER -> new Archer(unit);
            case KNIGHT -> new Knight(unit);
            case CATAPULT -> new Catapult(unit);
            case PIRATE -> new Pirate(unit);
            case NINJA -> new Ninja(unit);
            case VIKING -> new Viking(unit);
            default -> new Blank(unit);
        };
    }
}
