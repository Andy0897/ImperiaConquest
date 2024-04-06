package com.example.ImperiaConquest.Mine;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class MineService {
    EmpireService empireService;
    MineRepository mineRepository;

    @Lazy
    public MineService(EmpireService empireService, MineRepository mineRepository) {
        this.empireService = empireService;
        this.mineRepository = mineRepository;
    }

    public Mine setUpMine(Mine mine) {
        Faker faker = new Faker();
        mine.setGoldMiningCapacity(80);
        mine.setIronMiningCapacity(140);
        mine.setWoodMiningCapacity(220);
        mine.setName(faker.space().galaxy());
        return mine;
    }

    public String submitMining(Empire empire, Mine mine, String resource, Model model) {
        if(!checkIfCanMine(mine)) {
            model.addAttribute("canMine", false);
            model.addAttribute("empire", empire);
            mine = new Mine();
            model.addAttribute("mineBuy", mine);
            return "empire/show";
        }
        mining(empire, mine, resource);
        return "redirect:/empire/show";
    }

    public void mining(Empire empire, Mine mine, String resource) {
        if(resource.equals("gold")) {
            empire.setGold(empire.getGold() + mine.getGoldMiningCapacity());
        }
        else if(resource.equals("iron")) {
            empire.setIron(empire.getIron() + mine.getIronMiningCapacity());
        }
        else {
            empire.setWood(empire.getWood() + mine.getWoodMiningCapacity());
        }
        mine.setLastMining(LocalDateTime.now());
        empireService.updateEmpire(empire);
        updateMine(mine);
    }

    public boolean checkIfCanMine(Mine mine) {
        if(mine.getLastMining() != null) {
            return calculateHoursDifference(mine.getLastMining()) >= 1;
        }
        return true;
    }

    public static long calculateHoursDifference(LocalDateTime time) {
        long hoursDifference = ChronoUnit.HOURS.between(time, LocalDateTime.now());
        return Math.abs(hoursDifference);
    }

    public String submitUpgradeMine(Mine mine, Empire empire, String resource, Model model) {
        if(!checkIfCanPayUpgrade(empire, resource)) {
            model.addAttribute("canUpgradeMine", false);
            model.addAttribute("empire", empire);
            mine = new Mine();
            model.addAttribute("mineBuy", mine);
            return "empire/show";
        }
        payUpgrade(empire, resource);
        upgradeMine(mine);
        return "redirect:/empire/show";
    }

    public boolean checkIfCanPayUpgrade(Empire empire, String resource) {
        return resource.equals("gold") && empire.getGold() >= 50 ||
                resource.equals("iron") && empire.getIron() >= 100 ||
                resource.equals("wood") && empire.getWood() >= 200;
    }

    public void payUpgrade(Empire empire, String resource) {
        if(resource.equals("gold")) {
            empire.setGold(empire.getGold() - 50);
        }
        else if(resource.equals("iron")) {
            empire.setIron(empire.getIron() - 100);
        }
        else {
            empire.setWood(empire.getWood() - 200);
        }
        empireService.updateEmpire(empire);
    }

    public long getMinutesToMine(Mine mine) {
        if(checkIfCanMine(mine)) {
            return 0;
        }
        long minutes = 60 - ChronoUnit.MINUTES.between(mine.getLastMining(), LocalDateTime.now());
        return Math.abs(minutes);
    }

    public void upgradeMine(Mine mine) {
        mine.setGoldMiningCapacity(mine.getGoldMiningCapacity() + 10);
        mine.setIronMiningCapacity(mine.getIronMiningCapacity() + 20);
        mine.setWoodMiningCapacity(mine.getWoodMiningCapacity() + 40);
        updateMine(mine);
    }

    public void updateMine(Mine mine) {
        mineRepository.save(mine);
    }
}
