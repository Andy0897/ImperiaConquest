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
        if (checkIfCanMine(mine)) {
            mining(empire, mine, resource);
        }
        return "redirect:/empire/show";
    }

    private void mining(Empire empire, Mine mine, String resource) {
        if(resource.equals("gold")) {
            System.out.println(empire.getGold());
            empire.setGold(empire.getGold() + mine.getGoldMiningCapacity());
            System.out.println(empire.getGold());
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

    private static long calculateHoursDifference(LocalDateTime time) {
        long hoursDifference = ChronoUnit.HOURS.between(time, LocalDateTime.now());
        return Math.abs(hoursDifference);
    }
    public long getMinutesToMine(Mine mine) {
        if(checkIfCanMine(mine)) {
            return 0;
        }
        long minutes = 60 - ChronoUnit.MINUTES.between(mine.getLastMining(), LocalDateTime.now());
        return Math.abs(minutes);
    }

    public void updateMine(Mine mine) {
        mineRepository.save(mine);
    }
}
