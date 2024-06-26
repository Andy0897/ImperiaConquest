
package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Mine.Mine;
import com.example.ImperiaConquest.Mine.MineRepository;
import com.example.ImperiaConquest.Mine.MineService;
import com.example.ImperiaConquest.Unit.Structures.UnitItem;
import com.example.ImperiaConquest.Unit.UnitService;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmpireService {
    private final UnitService unitService;
    UserRepository userRepository;
    EmpireRepository empireRepository;
    MineRepository mineRepository;
    MineService mineService;
    BuildingService buildingService;

    @Autowired
    public EmpireService(UserRepository userRepository, EmpireRepository empireRepository, MineRepository mineRepository, MineService mineService, BuildingService buildingService, UnitService unitService) {
        this.userRepository = userRepository;
        this.empireRepository = empireRepository;
        this.mineRepository = mineRepository;
        this.mineService = mineService;
        this.buildingService = buildingService;
        this.unitService = unitService;
    }

    public Building getGarrisonBuildings(Empire empire) {
        return this.buildingService.getGarrisonBuildingsByEmpire(empire).orElseGet(Building::new);
    }

    public Building getQuartersBuildings(Empire empire) {
        return this.buildingService.getQuartersBuildingsByEmpire(empire).orElseGet(Building::new);
    }

    public Building getBarracksBuildings(Empire empire) {
        return this.buildingService.getBarracksBuildingsByEmpire(empire).orElseGet(Building::new);
    }

    public String submitSaveEmpire(Empire empire, BindingResult bindingResult, @AuthenticationPrincipal MyUserDetails myuserDetails, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("empire", empire);
            return "empire/add";
        } else {
            User user = this.userRepository.getUserByUsername(myuserDetails.getUsername());
            empire.setUser(user);
            this.setResources(empire);
            this.empireRepository.save(empire);
            return "redirect:/empire/add";
        }
    }

    public void setResources(Empire empire) {
        empire.setGold(100);
        empire.setIron(200);
        empire.setWood(400);
    }

    public Empire getEmpireByUsername(String username) {
        User user = this.userRepository.getUserByUsername(username);
        return this.empireRepository.getEmpireByUserId(user.getId());
    }

    public void buyMine(Mine mine, Empire empire, String resource) {
        this.payMine(empire, resource);
        empire.addMine(mine);
        this.mineRepository.save(mine);
        this.updateEmpire(empire);
    }

    public void reduceResources(Empire empire, Integer gold, Integer iron, Integer wood) {
        empire.setGold(empire.getGold() - gold);
        empire.setIron(empire.getIron() - iron);
        empire.setWood(empire.getWood() - wood);
        this.empireRepository.save(empire);
    }

    public void increaseResources(Empire empire, Integer gold, Integer iron, Integer wood) {
        empire.setGold(empire.getGold() + gold);
        empire.setIron(empire.getIron() + iron);
        empire.setWood(empire.getWood() + wood);
        this.empireRepository.save(empire);
    }

    public boolean hasEnoughResourcesForPurchase(Empire empire, Integer gold, Integer iron, Integer wood) {
        return empire.getGold() >= gold && empire.getIron() >= iron && empire.getWood() >= wood;
    }

    public void payMine(Empire empire, String resource) {
        if (resource.equals("gold")) {
            empire.setGold(empire.getGold() - 100);
        } else if (resource.equals("iron")) {
            empire.setIron(empire.getIron() - 200);
        } else {
            empire.setWood(empire.getWood() - 400);
        }

        this.updateEmpire(empire);
    }

    public String submitBuyMine(Empire empire, Mine mine, String resource) {
        if (this.checkIfCanBuyMine(empire, resource)) {
            this.buyMine(mine, empire, resource);
        }
        return "redirect:/empire/show";
    }

    public boolean checkIfCanBuyMine(Empire empire, String resource) {
        return resource.equals("gold") && empire.getGold() >= 100 || resource.equals("iron") && empire.getIron() >= 200 || resource.equals("wood") && empire.getWood() >= 400;
    }

    public void updateEmpire(Empire empire) {
        this.empireRepository.save(empire);
    }

    public Long getEmpireWins(Empire empire) {
        return (long) empire.getWins().size();
    }

    public EmpireRepository getEmpireRepository() {
        return this.empireRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public Empire getEmpireByPrincipal(Principal principal) {
        User user = this.userRepository.getUserByUsername(principal.getName());

        return this.getEmpireRepository().getEmpireByUserId(user.getId());
    }

    public Integer getEmpireDefenceHealth(Empire empire) {
        List<UnitItem> empireTroops = unitService.getUnitsByEmpire(empire);
        AtomicInteger defence = new AtomicInteger(0);

        empireTroops.forEach(unitItem -> {
            defence.addAndGet((unitItem.getHealth() * unitItem.getCount()));
        });

        return defence.get();
    }
}
