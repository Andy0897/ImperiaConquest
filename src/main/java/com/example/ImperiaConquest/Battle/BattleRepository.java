package com.example.ImperiaConquest.Battle;

import com.example.ImperiaConquest.Empire.Empire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BattleRepository extends CrudRepository<Battle, Long> {
//    @Query("SELECT b.level FROM Building b WHERE b.empire = :empire AND b.type = :type")
    @Query("SELECT battle FROM Battle battle WHERE battle.attacker = :empire OR battle.defender = :empire")
    List<Battle> findByAttackerOrDefenderId(Empire empire);
}
