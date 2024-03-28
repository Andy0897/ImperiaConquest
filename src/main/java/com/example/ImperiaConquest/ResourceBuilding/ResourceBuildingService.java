package com.example.ImperiaConquest.ResourceBuilding;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ResourceBuildingService {
    ResourceBuildingRepository resourceBuildingRepository;
    EmpireService empireService;

    public ResourceBuildingService(ResourceBuildingRepository resourceBuildingRepository, EmpireService empireService) {
        this.resourceBuildingRepository = resourceBuildingRepository;
        this.empireService = empireService;
    }

    private void mining(String resource, Empire empire, ResourceBuilding resourceBuilding) {
        if (resource.equals("gold")) {
            empire.setGold(resourceBuilding.getMiningCapacity());
        } else if (resource.equals("iron")) {
            empire.setIron(resourceBuilding.getMiningCapacity());
        } else {
            empire.setWood(resourceBuilding.getMiningCapacity());
        }
        resourceBuilding.setLastMining(LocalDateTime.now());
        updateResourceBuilding(resourceBuilding);
        empireService.updateEmpire(empire);
    }

    public String startMining(String resource, Empire empire, ResourceBuilding resourceBuilding, Model model) {
        if (checkIfCanMining(resourceBuilding)) {
            mining(resource, empire, resourceBuilding);
            return "redirect:";
        }
        model.addAttribute("canMining", false);
        return "redirect:";
    }

    private boolean checkIfCanMining(ResourceBuilding resourceBuilding) {
        return (calculateHoursSinceLastMining(resourceBuilding.getLastMining()) >= 1);
    }

    private Long calculateHoursSinceLastMining(LocalDateTime lastMining) {
        long hoursDifference = ChronoUnit.HOURS.between(lastMining, LocalDateTime.now());
        return Math.abs(hoursDifference);
    }

    public void updateResourceBuilding(ResourceBuilding resourceBuilding) {
        resourceBuildingRepository.save(resourceBuilding);
    }

    public void setResourceBuilding(ResourceBuilding resourceBuilding) {
        resourceBuilding.setMiningCapacity(20);
    }

    private boolean checkIfCanUpgradeResourceBuilding(String resource, Empire empire, ResourceBuilding resourceBuilding) {
        return resource.equals("gold") && empire.getGold() >= calculateUpgradePrice(resourceBuilding, resource) ||
                resource.equals("iron") && empire.getIron() >= calculateUpgradePrice(resourceBuilding, resource) ||
                resource.equals("wood") && empire.getWood() >= calculateUpgradePrice(resourceBuilding, resource);
    }

    private int calculateUpgradePrice(ResourceBuilding resourceBuilding, String resource) {
        if (resource.equals("gold")) {
            return resourceBuilding.getMiningCapacity() + 20;
        } else if (resource.equals("iron")) {
            return resourceBuilding.getMiningCapacity() + 50;
        } else {
            return resourceBuilding.getMiningCapacity() + 100;
        }
    }

    public String upgradeResourceBuilding(ResourceBuilding resourceBuilding, String resource, Empire empire, Model model) {
        if (checkIfCanUpgradeResourceBuilding(resource, empire, resourceBuilding)) {
            empireService.payUpgrade(empire, resource, calculateUpgradePrice(resourceBuilding, resource));
        }
        model.addAttribute("canUpgrade", false);
        return "redirect:";
    }
}