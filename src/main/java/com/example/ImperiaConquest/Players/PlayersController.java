package com.example.ImperiaConquest.Players;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireRepository;
import com.example.ImperiaConquest.Empire.EmpireService;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PlayersController {
    UserRepository userRepository;

    EmpireRepository empireRepository;

    EmpireService empireService;

    @Autowired
    public PlayersController(UserRepository userRepository, EmpireRepository empireRepository, EmpireService empireService) {
        this.userRepository = userRepository;
        this.empireRepository = empireRepository;
        this.empireService = empireService;
    }

    @GetMapping("/players")
    public String getEnemies(Model model, Principal principal){

        User user = userRepository.getUserByUsername(principal.getName());

        Empire userEmpire = empireRepository.getEmpireByUserId(user.getId());

        List<Empire> otherEmpires = empireRepository.findAllByIdNot(userEmpire.getId());

        model.addAttribute("empires", otherEmpires);
        model.addAttribute("empireService", empireService);

        return "players/players";
    }
}
