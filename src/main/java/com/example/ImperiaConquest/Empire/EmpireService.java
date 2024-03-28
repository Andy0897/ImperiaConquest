package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.Mine.Mine;
import com.example.ImperiaConquest.Mine.MineRepository;
import com.example.ImperiaConquest.Mine.MineService;
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
    MineRepository mineRepository;
    MineService mineService;

    @Autowired
    public EmpireService(UserRepository userRepository, EmpireRepository empireRepository, MineRepository mineRepository, MineService mineService) {
        this.userRepository = userRepository;
        this.empireRepository = empireRepository;
        this.mineRepository = mineRepository;
        this.mineService = mineService;
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

    private void buyMine(Mine mine, Empire empire, String resource) {
        payMine(empire, resource);
        empire.addMine(mine);
        mineRepository.save(mine);
        updateEmpire(empire);
    }

    private void payMine(Empire empire, String resource) {
        if(resource.equals("gold")) {
            empire.setGold(empire.getGold() - 100);
        }
        else if(resource.equals("iron")) {
            empire.setIron(empire.getIron() - 200);
        }
        else {
            empire.setWood(empire.getWood() - 400);
        }
        updateEmpire(empire);
    }

    public String submitBuyMine(Empire empire, Mine mine, String resource) {
        if(checkIfCanBuyMine(empire, resource)){
            buyMine(mine, empire, resource);
            return "redirect:/empire/show";
        }
    }

    private boolean checkIfCanBuyMine(Empire empire, String resource) {
        return resource.equals("gold") && empire.getGold() >= 100 ||
                resource.equals("iron") && empire.getIron() >= 200 ||
                resource.equals("wood") && empire.getWood() >= 400;
    }

    public void updateEmpire(Empire empire) {
        empireRepository.save(empire);
    }

    public Long getEmpireWins(Empire empire){
        return (long) empire.getWins().size();
    }
}