package com.example.ImperiaConquest.Mine;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class MineService {
    EmpireService empireService;

    @Lazy
    public MineService(EmpireService empireService) {
        this.empireService = empireService;
    }

    public Mine setUpMine(Mine mine) {
        mine.setGoldMiningCapacity(80);
        mine.setIronMiningCapacity(140);
        mine.setWoodMiningCapacity(220);
        return mine;
    }

    public String submitMining(Empire empire, Mine mine, String resource, Model model) {
        if(!checkIfCanMine(mine)) {
            model.addAttribute("canMine", false);
            model.addAttribute("empire", empire);
            model.addAttribute("mineBuy", new Mine());
            return "empire/show";
        }
        mining(empire, mine, resource);
        return "redirect:/empire/show";
    }

    private void mining(Empire empire, Mine mine, String resource) {
        if(resource.equals("gold")) {
            empire.setGold(empire.getGold() + mine.getGoldMiningCapacity());
        }
        else if(resource.equals("iron")) {
            empire.setIron(empire.getIron() + mine.getIronMiningCapacity());
        }
        else {
            empire.setWood(empire.getWood() + mine.getWoodMiningCapacity());
        }
        empireService.updateEmpire(empire);
    }

    private boolean checkIfCanMine(Mine mine) {
        return calculateHoursDifference(mine.getLastMining()) >= 1;
    }

    private static long calculateHoursDifference(LocalDateTime time) {
        long hoursDifference = ChronoUnit.HOURS.between(time, LocalDateTime.now());
        return Math.abs(hoursDifference);
    }
}
