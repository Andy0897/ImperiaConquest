package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.BattleLog.BattleLog;
import com.example.ImperiaConquest.Mine.Mine;
import com.example.ImperiaConquest.User.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "empires")
public class Empire {
    @Column(name = "empire_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    @OneToMany(mappedBy = "empire")
    private List<Mine> mines;
    @Column(name = "gold")
    private int gold;
    @Column(name = "wood")
    private int wood;
    @Column(name = "iron")
    private int iron;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "winner")
    private List<BattleLog> wins;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "winner")
    private List<BattleLog> losses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public List<Mine> getMines() {
        return mines;
    }

    public void setMines(List<Mine> mines) {
        this.mines = mines;
    }

    public List<BattleLog> getWins() {
        return wins;
    }

    public void setWins(List<BattleLog> wins) {
        this.wins = wins;
    }

    public List<BattleLog> getLosses() {
        return losses;
    }

    public void setLosses(List<BattleLog> losses) {
        this.losses = losses;
    }

    public void addMine(Mine mine) {
        mines.add(mine);
    }
}
