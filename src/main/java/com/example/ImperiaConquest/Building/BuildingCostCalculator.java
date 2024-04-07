package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Enums.BuildingTypes;
import com.example.ImperiaConquest.Enums.ResourceTypes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BuildingCostCalculator {
    Integer buildingLevel;
    BuildingTypes buildingType;

    public BuildingCostCalculator(Integer level, String type) {
        this.buildingLevel = level;
        this.buildingType = BuildingTypes.valueOf(type.toUpperCase());
    }

    public HashMap<String, Integer> getBuildingType() {
        HashMap var10000;
        switch (this.buildingType) {
            case BARRACKS:
                var10000 = initialBarracksValues();
                break;
            case GARRISON:
                var10000 = initialQuartersValues();
                break;
            case QUARTERS:
                var10000 = initialGarrisonValues();
                break;
            default:
                var10000 = new HashMap();
        }

        return var10000;
    }

    public HashMap<String, Integer> calculate() {
        HashMap<String, Integer> resourcesByType = this.getBuildingType();
        Iterator var2 = resourcesByType.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry)var2.next();
            String key = (String)entry.getKey();
            int value = (Integer)entry.getValue();
            int newValue = value * this.buildingLevel;
            resourcesByType.put(key, newValue);
        }

        return resourcesByType;
    }

    public static HashMap<String, Integer> initialGarrisonValues() {
        HashMap<String, Integer> initialValues = new HashMap();
        initialValues.put(ResourceTypes.GOLD.name(), 180);
        initialValues.put(ResourceTypes.IRON.name(), 290);
        initialValues.put(ResourceTypes.WOOD.name(), 540);
        return initialValues;
    }

    public static HashMap<String, Integer> initialQuartersValues() {
        HashMap<String, Integer> initialValues = new HashMap();
        initialValues.put(ResourceTypes.GOLD.name(), 700);
        initialValues.put(ResourceTypes.IRON.name(), 540);
        initialValues.put(ResourceTypes.WOOD.name(), 860);
        return initialValues;
    }

    public static HashMap<String, Integer> initialBarracksValues() {
        HashMap<String, Integer> initialValues = new HashMap();
        initialValues.put(ResourceTypes.GOLD.name(), 1500);
        initialValues.put(ResourceTypes.IRON.name(), 1800);
        initialValues.put(ResourceTypes.WOOD.name(), 3800);
        return initialValues;
    }
}
