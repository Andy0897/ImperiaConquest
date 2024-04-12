
package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.BattleLog.BattleLog;
import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Mine.Mine;
import com.example.ImperiaConquest.User.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(
    name = "empires"
)
public class Empire {
    @Column(
        name = "empire_id"
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private Long id;
    @OneToOne
    @JoinColumn(
        name = "user_id"
    )
    private User user;
    @NotEmpty(message = "This field can't be empty")
    @Size(min = 6, message = "Size must be greater than 6")
    @Size(max = 16, message = "Size must be smaller than 16")
    private String name;
    @OneToMany
    private List<Mine> mines;
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.ALL},
        mappedBy = "empire"
    )
    private List<Building> buildings;
    @Column(
        name = "gold"
    )
    private int gold;
    @Column(
        name = "wood"
    )
    private int wood;
    @Column(
        name = "iron"
    )
    private int iron;
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.ALL},
        mappedBy = "winner"
    )
    private List<BattleLog> wins;
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.ALL},
        mappedBy = "winner"
    )
    private List<BattleLog> losses;

    public Empire() {
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getWood() {
        return this.wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getIron() {
        return this.iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public List<Mine> getMines() {
        return this.mines;
    }

    public void setMines(List<Mine> mines) {
        this.mines = mines;
    }

    public List<BattleLog> getWins() {
        return this.wins;
    }

    public void setWins(List<BattleLog> wins) {
        this.wins = wins;
    }

    public List<BattleLog> getLosses() {
        return this.losses;
    }

    public void setLosses(List<BattleLog> losses) {
        this.losses = losses;
    }

    public void addMine(Mine mine) {
        this.mines.add(mine);
    }

    public List<Building> getBuildings() {
        return this.buildings;
    }
}
