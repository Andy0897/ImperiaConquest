package com.example.ImperiaConquest.BattleLog;

import com.example.ImperiaConquest.Empire.Empire;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "battle_logs")
public class BattleLog {

    @Column(name = "battle_log_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "attacker_id")
    @ManyToOne
    private  Empire attacker;

    @JoinColumn(name = "defender_id")
    @ManyToOne
    private  Empire defender;

    @JoinColumn(name = "winner_id")
    @ManyToOne
    private Empire winner;


    private LocalDateTime datetime;

    public BattleLog() {
        this.datetime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



   public LocalDateTime getDatetime() {
       return datetime;
   }

   public void setDatetime(LocalDateTime datetime) {
       this.datetime = datetime;
   }

    public Empire getAttacker() {
        return attacker;
    }

    public void setAttacker(Empire attacker) {
        this.attacker = attacker;
    }

    public Empire getDefender() {
        return defender;
    }

    public void setDefender(Empire defender) {
        this.defender = defender;
    }

    public Empire getWinner() {
        return winner;
    }

    public void setWinner(Empire winner) {
        this.winner = winner;
    }


    //ToDo: Add stolen resources.*/
}
