package com.example.ImperiaConquest.Battle;

import com.example.ImperiaConquest.Empire.Empire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleService {

    BattleRepository battleRepository;

    public BattleService(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    public BattleRepository getBattleRepository() {
        return battleRepository;
    }

    public List<Battle> getBattleHistoryForEmpire(Empire empire) {
        return this.battleRepository.findByAttackerOrDefenderId(empire);
    }
}
