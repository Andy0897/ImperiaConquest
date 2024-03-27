package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.ResourceBuilding.ResourceBuilding;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class EmpireService {
    UserRepository userRepository;
    EmpireRepository empireRepository;

    @Autowired
    public EmpireService(UserRepository userRepository, EmpireRepository empireRepository) {
        this.userRepository = userRepository;
        this.empireRepository = empireRepository;
    }

    public String saveEmpire(@ModelAttribute Empire empire, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myuserDetails) {
        if (bindingResult.hasErrors()) {
            return "empire/add";
        }
        User user = userRepository.getUserByUsername(myuserDetails.getUsername());
        empire.setUser(user);
        setResources(empire);
        empireRepository.save(empire);
        return "redirect:/empire/add";
    }

    private void setResources(Empire empire) {
        empire.setGold(100);
        empire.setWood(100);
        empire.setIron(100);
    }

    public Empire getEmpireByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        return empireRepository.getEmpireByUserId(user.getId());
    }

    public void updateEmpire(Empire empire) {
        empireRepository.save(empire);
    }

    public void buyResourceBuilding(Empire empire, ResourceBuilding resourceBuilding, String resource, int price) {
        if (resource.equals("gold")) {
            empire.setGold(empire.getGold() - price);
        }
        else if (resource.equals("iron")) {
            empire.setIron(empire.getIron() - price);
        }
        else {
            empire.setWood(empire.getWood() - price);
        }
        empire.addResourceBuilding(resourceBuilding);
        updateEmpire(empire);
    }

    public void payUpgrade(Empire empire, String resource, int price) {
        if (resource.equals("gold")) {
            empire.setGold(empire.getGold() - price);
        }
        else if (resource.equals("iron")) {
            empire.setIron(empire.getIron() - price);
        }
        else {
            empire.setWood(empire.getWood() - price);
        }
        updateEmpire(empire);
    }
}
