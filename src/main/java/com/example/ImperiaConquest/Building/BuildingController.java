package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import com.example.ImperiaConquest.Enums.ResourceTypes;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/building"})
public class BuildingController {
    BuildingService buildingService;
    EmpireService empireService;

    public BuildingController(BuildingService buildingService, EmpireService empireService) {
        this.buildingService = buildingService;
        this.empireService = empireService;
    }

    @PostMapping({"/save"})
    public String saveBuilding(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        Building building = new Building();
        Empire empire = (Empire)this.empireService.getEmpireRepository().findById(Long.parseLong(request.getParameter("empire_id"))).orElseThrow(() -> {
            return new EntityNotFoundException("Empire Not Found");
        });
        HashMap<String, Integer> buildingCost = (new BuildingCostCalculator(1, request.getParameter("type"))).calculate();
        boolean hasEnoughResources = this.empireService.hasEnoughResourcesForPurchase(empire, (Integer)buildingCost.get(ResourceTypes.GOLD.name()), (Integer)buildingCost.get(ResourceTypes.IRON.name()), (Integer)buildingCost.get(ResourceTypes.WOOD.name()));
        if (!hasEnoughResources) {
            String warningMessage = "You don't have enough resources to purchase this Building. GOT MORE MONEY FOOOOOL!";
            redirectAttributes.addFlashAttribute("warningMessage", warningMessage);
            return "redirect:/empire/show";
        } else {
            building.setEmpire(empire);
            building.setType(request.getParameter("type"));
            building.setCollected_at(LocalDateTime.now());
            building.setLevel(1L);
            this.buildingService.getBuildingRepository().save(building);
            this.empireService.reduceResource(empire, (Integer)buildingCost.get(ResourceTypes.GOLD.name()), (Integer)buildingCost.get(ResourceTypes.IRON.name()), (Integer)buildingCost.get(ResourceTypes.WOOD.name()));
            return "redirect:/empire/show";
        }
    }
}
