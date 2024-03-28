package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.Mine.Mine;
import com.example.ImperiaConquest.Mine.MineService;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empire")
public class EmpireController {
    UserRepository userRepository;
    EmpireService empireService;
    EmpireRepository empireRepository;
    MineService mineService;

    public EmpireController(UserRepository userRepository, EmpireService empireService, EmpireRepository empireRepository, MineService mineService) {
        this.userRepository = userRepository;
        this.empireService = empireService;
        this.empireRepository = empireRepository;
        this.mineService = mineService;
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
        model.addAttribute("empire", empire);
        model.addAttribute("mineBuy", new Mine());
        return "empire/show";
    }

    @PostMapping("/buy-mine")
    public String submitBuyMine(Mine mine, Model model, Principal principal) {
        mine = mineService.setUpMine(mine);
        User user = userRepository.getUserByUsername(principal.getName());
        Empire empire = empireRepository.getEmpireByUserId(user.getId());
        return empireService.submitBuyMine(empire, mine, "gold", model);
    }
}
