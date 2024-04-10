package com.example.ImperiaConquest.Unit;

import com.example.ImperiaConquest.Building.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

    @Autowired
    BuildingRepository buildingRepository;

    public UnitService() {
    }

    public BuildingRepository getBuildingRepository() {
        return this.buildingRepository;
    }
}
