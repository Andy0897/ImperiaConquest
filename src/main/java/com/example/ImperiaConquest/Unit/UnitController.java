package com.example.ImperiaConquest.Unit;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping({"/unit"})

public class UnitController {
    BuildingService buildingService;
    EmpireService empireService;
    UnitService unitService;

    public UnitController(BuildingService buildingService, EmpireService empireService, UnitService unitService) {
        this.buildingService = buildingService;
        this.empireService = empireService;
        this.unitService = unitService;
    }

    @PostMapping({"/save"})
    public String saveUnit(RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Empire empire = this.empireService.getEmpireRepository().findById(Long.parseLong(request.getParameter("empire_id"))).orElseThrow(() -> new EntityNotFoundException("Empire Not Found"));

        if (empire == null) {
            redirectAttributes.addFlashAttribute("message", "Empire Not Found");
            return "redirect:/empire/show";
        }
        Building building = this.buildingService.getBuildingRepository().findById(Long.valueOf(request.getParameter("building_id"))).orElseGet(Building::new);

        if (building.getId() == null) {
            redirectAttributes.addFlashAttribute("message", "Building Not Found");
            return "redirect:/building/show";
        }

        String unitType = request.getParameter("type");
        Unit battleUnit = unitService.getUnitRepository().findByEmpireAndType(empire, unitType).orElseGet(Unit::new);

        battleUnit.setEmpire(empire);
        battleUnit.setType(unitType);
        battleUnit.setCount(battleUnit.getCount() + unitService.calculateUnitsPerHour(building, unitType));
        battleUnit.setBuilding(building);

        unitService.getUnitRepository().save(battleUnit);

        building.setCollected_at(LocalDateTime.now());
        buildingService.getBuildingRepository().save(building);

        redirectAttributes.addFlashAttribute("message", "⚔️⚔️ Unit Saved Successfully ⚔️⚔️");
        redirectAttributes.addFlashAttribute("messageClass", "font-weight-bold text-center center alert-success");

        return "redirect:/empire/show";
    }
}
