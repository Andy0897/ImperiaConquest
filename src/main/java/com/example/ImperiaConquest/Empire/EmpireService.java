
package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Mine.Mine;
import com.example.ImperiaConquest.Mine.MineRepository;
import com.example.ImperiaConquest.Mine.MineService;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class EmpireService {
    UserRepository userRepository;
    EmpireRepository empireRepository;
    MineRepository mineRepository;
    MineService mineService;
    BuildingService buildingService;

    @Autowired
    public EmpireService(UserRepository userRepository, EmpireRepository empireRepository, MineRepository mineRepository, MineService mineService, BuildingService buildingService) {
        this.userRepository = userRepository;
        this.empireRepository = empireRepository;
        this.mineRepository = mineRepository;
        this.mineService = mineService;
        this.buildingService = buildingService;
    }

    public Building getGarrisonBuildings(Empire empire) {
        return (Building)this.buildingService.getGarrisonBuildingsByEmpire(empire).orElseGet(Building::new);
    }

    public Building getQuartersBuildings(Empire empire) {
        return (Building)this.buildingService.getQuartersBuildingsByEmpire(empire).orElseGet(Building::new);
    }

    public Building getBarracksBuildings(Empire empire) {
        return (Building)this.buildingService.getBarracksBuildingsByEmpire(empire).orElseGet(Building::new);
    }

    public String saveEmpire(@ModelAttribute Empire empire, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myuserDetails) {
        if (bindingResult.hasErrors()) {
            return "empire/add";
        } else {
            User user = this.userRepository.getUserByUsername(myuserDetails.getUsername());
            empire.setUser(user);
            this.setResources(empire);
            this.empireRepository.save(empire);
            return "redirect:/empire/add";
        }
    }

    private void setResources(Empire empire) {
        empire.setGold(100);
        empire.setIron(200);
        empire.setWood(400);
    }

    public Empire getEmpireByUsername(String username) {
        User user = this.userRepository.getUserByUsername(username);
        return this.empireRepository.getEmpireByUserId(user.getId());
    }

    private void buyMine(Mine mine, Empire empire, String resource) {
        this.payMine(empire, resource);
        empire.addMine(mine);
        this.mineRepository.save(mine);
        this.updateEmpire(empire);
    }

    public void reduceResource(Empire empire, Integer gold, Integer iron, Integer wood) {
        empire.setGold(empire.getGold() - gold);
        empire.setIron(empire.getIron() - iron);
        empire.setWood(empire.getWood() - wood);
        this.empireRepository.save(empire);
    }

    public boolean hasEnoughResourcesForPurchase(Empire empire, Integer gold, Integer iron, Integer wood) {
        return empire.getGold() >= gold && empire.getIron() >= iron && empire.getWood() >= wood;
    }

    private void payMine(Empire empire, String resource) {
        if (resource.equals("gold")) {
            empire.setGold(empire.getGold() - 100);
        } else if (resource.equals("iron")) {
            empire.setIron(empire.getIron() - 200);
        } else {
            empire.setWood(empire.getWood() - 400);
        }

        this.updateEmpire(empire);
    }

    public String submitBuyMine(Empire empire, Mine mine, String resource, Model model) {
        if (this.checkIfCanBuyMine(empire, resource)) {
            this.buyMine(mine, empire, resource);
        }

        return "redirect:/empire/show";
    }

    private boolean checkIfCanBuyMine(Empire empire, String resource) {
        return resource.equals("gold") && empire.getGold() >= 100 || resource.equals("iron") && empire.getIron() >= 200 || resource.equals("wood") && empire.getWood() >= 400;
    }

    public void updateEmpire(Empire empire) {
        this.empireRepository.save(empire);
    }

    public Long getEmpireWins(Empire empire) {
        return (long)empire.getWins().size();
    }

    public EmpireRepository getEmpireRepository() {
        return this.empireRepository;
    }
}
