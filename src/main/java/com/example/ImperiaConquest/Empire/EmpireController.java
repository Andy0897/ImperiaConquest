package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.Battle.BattleService;
import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Building.BuildingsDTO;
import com.example.ImperiaConquest.Mine.Mine;
import com.example.ImperiaConquest.Mine.MineService;
import com.example.ImperiaConquest.Unit.UnitService;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import com.example.ImperiaConquest.Utils.TimeHelpers;
import jakarta.validation.Valid;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping({"/empire"})
public class EmpireController {
    UserRepository userRepository;
    EmpireService empireService;
    MineService mineService;
    BuildingService buildingService;
    UnitService unitService;
    BattleService battleService;

    public EmpireController(
            UserRepository userRepository,
            EmpireService empireService,
            MineService mineService,
            BuildingService buildingService,
            UnitService unitService,
            BattleService battleService
    ) {
        this.userRepository = userRepository;
        this.empireService = empireService;
        this.mineService = mineService;
        this.buildingService = buildingService;
        this.unitService = unitService;
        this.battleService = battleService;
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
    public String saveEmpire(@ModelAttribute @Valid Empire empire, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myuserDetails, Model model) {
        return this.empireService.submitSaveEmpire(empire, bindingResult, myuserDetails, model);
    }


    @AttributeAccessor

    @GetMapping({"/show"})
    public String showEmpire(Model model, Principal principal) {
        if (this.empireService.getEmpireByUsername(principal.getName()) == null) {
            return "redirect:/empire/add";
        } else {
            Empire empire = this.empireService.getEmpireByUsername(principal.getName());
            BuildingsDTO buildingsDTO = new BuildingsDTO(empire, empireService, buildingService);

            User user = userRepository.getUserByUsername(principal.getName());

            Empire userEmpire = this.empireService.getEmpireRepository().getEmpireByUserId(user.getId());

            List<Empire> otherEmpires = this.empireService.getEmpireRepository().findAllByIdNot(userEmpire.getId());

            model.addAttribute("empires", otherEmpires);
            model.addAttribute("empireService", empireService);
            model.addAttribute("empire", empire);
            model.addAttribute("mineBuy", new Mine());
            model.addAttribute("mineService", this.mineService);
            model.addAttribute("empireService", this.empireService);
            model.addAttribute("buildingService", this.buildingService);
            model.addAttribute("unitService", this.unitService);
            model.addAttribute("battleService", battleService);
            model.addAttribute("empireBuildings", buildingsDTO.getBuildings());
            model.addAttribute("buildingStructures", buildingsDTO.getBuildingStructures());
            model.addAttribute("timeHelpers", new TimeHelpers());

            return "empire/show";
        }
    }

    @PostMapping({"/buy-mine/{resource}"})
    public String submitBuyMine(@PathVariable("resource") String resource, Mine mine, Principal principal) {
        mine = this.mineService.setUpMine(mine);
        User user = this.userRepository.getUserByUsername(principal.getName());
        Empire empire = this.empireService.getEmpireRepository().getEmpireByUserId(user.getId());
        return this.empireService.submitBuyMine(empire, mine, resource);
    }

    @PostMapping({"/submit-mining/{resource}"})
    public String submitMining(@RequestParam Long mineId, @PathVariable("resource") String resource, Model model, Principal principal) {
        User user = this.userRepository.getUserByUsername(principal.getName());
        Empire empire = this.empireService.getEmpireRepository().getEmpireByUserId(user.getId());
        Mine mine = this.mineService.getMineRepository().getMineById(mineId);
        return this.mineService.submitMining(empire, mine, resource, model);
    }

    @PostMapping({"/submit-mine-upgrade/{resource}"})
    public String submitUpgradeMine(@RequestParam Long mineId, @PathVariable("resource") String resource, Model model, Principal principal) {
        User user = this.userRepository.getUserByUsername(principal.getName());
        Empire empire = this.empireService.getEmpireRepository().getEmpireByUserId(user.getId());
        Mine mine = this.mineService.getMineRepository().getMineById(mineId);
        return this.mineService.submitUpgradeMine(mine, empire, resource, model);
    }
}
