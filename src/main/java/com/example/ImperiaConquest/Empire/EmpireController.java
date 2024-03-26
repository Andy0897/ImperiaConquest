package com.example.ImperiaConquest.Empire;

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

@Controller
@RequestMapping("/empire")
public class EmpireController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmpireService empireService;

    @GetMapping("/add")
    public String addEmpire(Model model, Principal principal) {
        Empire empire = empireService.getEmpireByUsername(principal.getName());
        if(empire != null) {
            return "redirect:/empire/show";
        }
        model.addAttribute("empire", new Empire());
        return "empire/add";
    }

    @PostMapping("/save")
    public String saveEmpire(@ModelAttribute Empire empire, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myuserDetails, Principal principal){
        return empireService.saveEmpire(empire, bindingResult, myuserDetails);
    }

    @GetMapping("/show")
    public String showEmpire(Model model, Principal principal){
        Empire empire1 = empireService.getEmpireByUsername(principal.getName());
        model.addAttribute("empire", empire1);
        return "empire/show";
    }
}
