package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Enums.BuildingTypes;
import com.example.ImperiaConquest.Enums.ResourceTypes;

import java.util.HashMap;
import java.util.Map;

public class BuildingCostCalculator {

    Integer buildingLevel;
    BuildingTypes buildingType;

    public BuildingCostCalculator(Integer level, String type) {
        buildingLevel = level;
        buildingType = BuildingTypes.valueOf(type.toUpperCase());
    }

    public HashMap<String, Integer> calculate() {

        if (buildingType == BuildingTypes.BARRACKS) {
            return barracksValue();
        } else if (buildingType == BuildingTypes.GARRISON) {
            return garrisonValues();
        } else if (buildingType == BuildingTypes.QUARTERS) {
            return quarterValues();
        } else {
            return new HashMap<String, Integer>();
        }
    }

    public HashMap<String, Integer> garrisonValues() {
        HashMap<String, Integer> initialValues = new HashMap<String, Integer>();
        initialValues.put(ResourceTypes.GOLD.name(), 180 * (buildingLevel + 1));
        initialValues.put(ResourceTypes.IRON.name(), 290 * (buildingLevel + 1));
        initialValues.put(ResourceTypes.WOOD.name(), 540 * (buildingLevel + 1));

        return initialValues;
    }

    public HashMap<String, Integer> quarterValues() {
        HashMap<String, Integer> initialValues = new HashMap<String, Integer>();
        initialValues.put(ResourceTypes.GOLD.name(), 700 * (buildingLevel + 1));
        initialValues.put(ResourceTypes.IRON.name(), 540 * (buildingLevel + 1));
        initialValues.put(ResourceTypes.WOOD.name(), 860 * (buildingLevel + 1));

        return initialValues;
    }

    public HashMap<String, Integer> barracksValue() {
        HashMap<String, Integer> initialValues = new HashMap<String, Integer>();
        initialValues.put(ResourceTypes.GOLD.name(), 1500 * (buildingLevel + 1));
        initialValues.put(ResourceTypes.IRON.name(), 1800 * (buildingLevel + 1));
        initialValues.put(ResourceTypes.WOOD.name(), 3800 * (buildingLevel + 1));

        return initialValues;
    }
}
