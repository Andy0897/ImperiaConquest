package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Building.BuildingsDTO;
import com.example.ImperiaConquest.Mine.Mine;
import com.example.ImperiaConquest.Mine.MineRepository;
import com.example.ImperiaConquest.Mine.MineService;
import com.example.ImperiaConquest.Unit.UnitService;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import com.example.ImperiaConquest.Utils.TimeHelpers;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping({"/empire"})
public class EmpireController {
    UserRepository userRepository;
    EmpireService empireService;
    EmpireRepository empireRepository;
    MineService mineService;
    MineRepository mineRepository;
    BuildingService buildingService;
    UnitService unitService;

    public EmpireController(UserRepository userRepository, EmpireService empireService, EmpireRepository empireRepository, MineService mineService, MineRepository mineRepository, BuildingService buildingService, UnitService unitService) {
        this.userRepository = userRepository;
        this.empireService = empireService;
        this.empireRepository = empireRepository;
        this.mineService = mineService;
        this.mineRepository = mineRepository;
        this.buildingService = buildingService;
        this.unitService = unitService;
    }

    @GetMapping({"/add"})
    public String addEmpire(Model model, Principal principal) {
        Empire empire = this.empireService.getEmpireByUsername(principal.getName());
        if (empire != null) {
            return "redirect:/empire/show";
        } else {
            model.addAttribute("empire", new Empire());
            return "empire/add";
        }
    }

    @PostMapping({"/save"})
    public String saveEmpire(@ModelAttribute Empire empire, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myuserDetails, Principal principal) {
        return this.empireService.saveEmpire(empire, bindingResult, myuserDetails);
    }


    @AttributeAccessor

    @GetMapping({"/show"})
    public String showEmpire(Model model, Principal principal) {
        if (this.empireService.getEmpireByUsername(principal.getName()) == null) {
            return "redirect:/empire/add";
        } else {
            Empire empire = this.empireService.getEmpireByUsername(principal.getName());
            String resource = "";

            BuildingsDTO buildingsDTO = new BuildingsDTO(empire, empireService, buildingService);

            model.addAttribute("empire", empire);
            model.addAttribute("mineBuy", new Mine());
            model.addAttribute("mineService", this.mineService);
            model.addAttribute("empireService", this.empireService);
            model.addAttribute("buildingService", this.buildingService);
            model.addAttribute("unitService", this.unitService);
            model.addAttribute("empireBuildings", buildingsDTO.getBuildings());
            model.addAttribute("buildingStructures", buildingsDTO.getBuildingStructures());
            model.addAttribute("timeHelpers", new TimeHelpers());

            return "empire/show";
        }
    }

    @PostMapping({"/buy-mine/{resource}"})
    public String submitBuyMine(@PathVariable("resource") String resource, Mine mine, Model model, Principal principal) {
        mine = this.mineService.setUpMine(mine);
        User user = this.userRepository.getUserByUsername(principal.getName());
        Empire empire = this.empireRepository.getEmpireByUserId(user.getId());
        return this.empireService.submitBuyMine(empire, mine, resource, model);
    }

    @PostMapping({"/submit-mining/{resource}"})
    public String submitMining(@RequestParam Long mineId, @PathVariable("resource") String resource, Model model, Principal principal) {
        User user = this.userRepository.getUserByUsername(principal.getName());
        Empire empire = this.empireRepository.getEmpireByUserId(user.getId());
        Mine mine = this.mineRepository.getMineById(mineId);
        return this.mineService.submitMining(empire, mine, resource, model);
    }

    @PostMapping({"/submit-mine-upgrade/{resource}"})
    public String submitUpgradeMine(@RequestParam Long mineId, @PathVariable("resource") String resource, Model model, Principal principal) {
        User user = this.userRepository.getUserByUsername(principal.getName());
        Empire empire = this.empireRepository.getEmpireByUserId(user.getId());
        Mine mine = this.mineRepository.getMineById(mineId);
        return this.mineService.submitUpgradeMine(mine, empire, resource, model);
    }
}
