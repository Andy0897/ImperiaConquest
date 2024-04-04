package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/building")
public class BuildingController {

    BuildingService buildingService;
    EmpireService empireService;

    public BuildingController(BuildingService buildingService, EmpireService empireService) {
        this.buildingService = buildingService;
        this.empireService = empireService;
    }

    @PostMapping("/save")
    public String saveBuilding(HttpServletRequest request) {
        Building building = new Building();
        Empire empire = empireService.getEmpireRepository()
                .findById(Long.parseLong(request.getParameter("empire_id")))
                .orElseThrow(() -> new EntityNotFoundException("Empire Not Found"));


        building.setEmpire(empire);
        building.setType(request.getParameter("type"));
        building.setCollected_at(LocalDateTime.now());

        buildingService.getBuildingRepository().save(building);

        return "redirect:/empire/show";
    }
}
