package com.example.ImperiaConquest.Battle;

import com.example.ImperiaConquest.Empire.Empire;
import com.example.ImperiaConquest.Empire.EmpireService;
import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Unit.Structures.UnitItem;
import com.example.ImperiaConquest.Unit.Unit;
import com.example.ImperiaConquest.Unit.UnitService;
import com.example.ImperiaConquest.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Controller
@RequestMapping({"/battle"})

public class BattleController {
    BattleService battleService;
    EmpireService empireService;
    UnitService unitService;

    public BattleController(BattleService battleService, EmpireService empireService, UnitService unitService) {
        this.empireService = empireService;
        this.unitService = unitService;
        this.battleService = battleService;
    }

    @GetMapping({"/{id}"})
    public String prepareForBattle(@PathVariable Long id, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        Empire empire = this.empireService.getEmpireByPrincipal(principal);
        Empire defendingEmpire = this.empireService.getEmpireRepository().findById(id).orElseGet(Empire::new);

        if (empire == null || defendingEmpire.getId() == null) {
            redirectAttributes.addFlashAttribute("message", "Empire Not Found.");
            redirectAttributes.addFlashAttribute("messageClass", "alert-danger text-white font-weight-bold text-center center");
            return "redirect:/empire/show";
        }

        List<UnitItem> battleTroops = unitService.getUnitsByEmpire(empire);

        model.addAttribute("empire", empire);
        model.addAttribute("defendingEmpire", defendingEmpire);
        model.addAttribute("troops", battleTroops);

        return "battle/prepare";
    }

    @PostMapping({"/start"})
    public String startBattle(RedirectAttributes redirectAttributes, HttpServletRequest request, Principal principal) {
        Empire attackingEmpire = this.empireService.getEmpireByPrincipal(principal);
        Battle battle = new Battle();
        Long defendingEmpireId = Long.valueOf(request.getParameter("defending_empire_id"));
        Empire defendingEmpire = this.empireService.getEmpireRepository().findById(defendingEmpireId).orElseGet(Empire::new);

        if (defendingEmpire.getId() == null) {
            redirectAttributes.addFlashAttribute("message", "Empire Not Found.");
            redirectAttributes.addFlashAttribute("messageClass", "bg-danger");

            return "redirect:/empire/show";
        }

        AtomicInteger attackingEmpireDMG = new AtomicInteger();
        Map<UnitItem, Integer> troopsData = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(name -> {
            if (!UnitTypes.contains(name.toUpperCase())) {
                return;
            }

            int count = Integer.parseInt(request.getParameter(name));
            Unit unit = unitService.getUnitRepository().findByEmpireAndType(attackingEmpire, name.toUpperCase()).orElseGet(Unit::new);

            if (unit.getId() == null) {
                return;
            }

            UnitItem unitTroopInstance = unitService.getUnitInstanceByType(unit);
            troopsData.put(unitTroopInstance, count);
            attackingEmpireDMG.addAndGet((unitTroopInstance.getAttack() * count));
        });

        Integer defendingEmpireDefenceHealth = empireService.getEmpireDefenceHealth(defendingEmpire);
        Empire winnerEmpire;


        if (attackingEmpireDMG.get() > defendingEmpireDefenceHealth) {
            redirectAttributes.addFlashAttribute("messageClass", "bg-success text-white");
            redirectAttributes.addFlashAttribute("message", "You Troops return victorious");
            winnerEmpire = attackingEmpire;

            Integer plunderedGold = (int) Math.round(defendingEmpire.getGold() * 0.1);
            Integer plunderedIron = (int) Math.round(defendingEmpire.getIron() * 0.1);
            Integer plunderedWood = (int) Math.round(defendingEmpire.getWood() * 0.1);

            empireService.reduceResources(defendingEmpire, plunderedGold, plunderedIron, plunderedWood);
            empireService.increaseResources(attackingEmpire, plunderedGold, plunderedIron, plunderedWood);

        } else {
            redirectAttributes.addFlashAttribute("messageClass", "bg-danger text-white");
            redirectAttributes.addFlashAttribute("message", "Your campaign has failed. Your troops perish");
            winnerEmpire = defendingEmpire;
            for (UnitItem troopInstance : troopsData.keySet()) {
                Integer count = troopsData.get(troopInstance);
                troopInstance.reduceUnit(count, unitService);
            }
        }

        battle.setAttacker(attackingEmpire);
        battle.setDefender(defendingEmpire);
        battle.setWinner(winnerEmpire);

        battleService.battleRepository.save(battle);


        return "redirect:/empire/show";
    }
}
