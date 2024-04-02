package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.Mine.Mine;
import com.example.ImperiaConquest.Mine.MineRepository;
import com.example.ImperiaConquest.Mine.MineService;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empire")
public class EmpireController {
    UserRepository userRepository;
    EmpireService empireService;
    EmpireRepository empireRepository;
    MineService mineService;
    MineRepository mineRepository;

    public EmpireController(UserRepository userRepository, EmpireService empireService, EmpireRepository empireRepository, MineService mineService, MineRepository mineRepository) {
        this.userRepository = userRepository;
        this.empireService = empireService;
        this.empireRepository = empireRepository;
        this.mineService = mineService;
        this.mineRepository = mineRepository;
    }

    @GetMapping("/add")
    public String addEmpire(Model model, Principal principal) {
        Empire empire = empireService.getEmpireByUsername(principal.getName());
        if (empire != null) {
            return "redirect:/empire/show";
        }
        model.addAttribute("empire", new Empire());
        return "empire/add";
    }

    @PostMapping("/save")
    public String saveEmpire(@ModelAttribute Empire empire, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myuserDetails, Principal principal) {
        return empireService.saveEmpire(empire, bindingResult, myuserDetails);
    }

    @GetMapping("/show")
    public String showEmpire(Model model, Principal principal) {
        Empire empire = empireService.getEmpireByUsername(principal.getName());
        String resource = "";
        model.addAttribute("empire", empire);
        model.addAttribute("mineBuy", new Mine());
        model.addAttribute("mineService", mineService);
        return "empire/show";
    }

    @PostMapping("/buy-mine/{resource}")
    public String submitBuyMine(@PathVariable("resource") String resource, Mine mine, Model model, Principal principal) {
        mine = mineService.setUpMine(mine);
        User user = userRepository.getUserByUsername(principal.getName());
        Empire empire = empireRepository.getEmpireByUserId(user.getId());
        return empireService.submitBuyMine(empire, mine, resource, model);
    }

    @PostMapping("/submit-mining/{resource}")
    public String submitMining(@RequestParam Long mineId, @PathVariable("resource") String resource, Model model, Principal principal) {
        User user = userRepository.getUserByUsername(principal.getName());
        Empire empire = empireRepository.getEmpireByUserId(user.getId());
        Mine mine = mineRepository.getMineById(mineId);
        return mineService.submitMining(empire, mine, resource, model);
    }
}
