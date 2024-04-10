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

    public HashMap<String, Integer> getBuildingType() {
        return switch (buildingType) {
            case BARRACKS -> initialBarracksValues();
            case GARRISON -> initialQuartersValues();
            case QUARTERS -> initialGarrisonValues();
            default -> new HashMap<String, Integer>();
        };
    }

    public HashMap<String, Integer> calculate() {
        HashMap<String, Integer> resourcesByType = getBuildingType();


        for (Map.Entry<String, Integer> entry : resourcesByType.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();

            //TODO: Maybe increase in steps instead of linear
            int newValue = value * (buildingLevel + 1);
            resourcesByType.put(key, newValue);
        }

        return resourcesByType;
    }

    public static HashMap<String, Integer> initialGarrisonValues() {
        HashMap<String, Integer> initialValues = new HashMap<String, Integer>();
        initialValues.put(ResourceTypes.GOLD.name(), 180);
        initialValues.put(ResourceTypes.IRON.name(), 290);
        initialValues.put(ResourceTypes.WOOD.name(), 540);

        return initialValues;
    }

    public static HashMap<String, Integer> initialQuartersValues() {
        HashMap<String, Integer> initialValues = new HashMap<String, Integer>();
        initialValues.put(ResourceTypes.GOLD.name(), 700);
        initialValues.put(ResourceTypes.IRON.name(), 540);
        initialValues.put(ResourceTypes.WOOD.name(), 860);

        return initialValues;
    }

    public static HashMap<String, Integer> initialBarracksValues() {
        HashMap<String, Integer> initialValues = new HashMap<String, Integer>();
        initialValues.put(ResourceTypes.GOLD.name(), 1500);
        initialValues.put(ResourceTypes.IRON.name(), 1800);
        initialValues.put(ResourceTypes.WOOD.name(), 3800);

        return initialValues;
    }
}
