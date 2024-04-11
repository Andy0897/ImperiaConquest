package com.example.ImperiaConquest.Interfaces;

import com.example.ImperiaConquest.Building.Building;

import java.util.Map;

public interface BuildingStructureInterface {

    public String getName();
    public String getType();
    public Building getBuilding();
    public void setBuilding();
    public Boolean exists();
    public Integer getBuildingGoldCost();
    public Integer getBuildingIronCost();
    public Integer getBuildingWoodCost();
    public Map<String, UnitInterface> getUnits();
}

