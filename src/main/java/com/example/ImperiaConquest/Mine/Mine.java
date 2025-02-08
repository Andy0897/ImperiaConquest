package com.example.ImperiaConquest.Mine;

import com.example.ImperiaConquest.Empire.Empire;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mines")
public class Mine {
    @Column(name = "mine_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "gold_mining_capacity")
    private int goldMiningCapacity;
    @Column(name = "iron_mining_capacity")
    private int ironMiningCapacity;
    @Column(name = "wood_mining_capacity")
    private int woodMiningCapacity;
    @Column(name = "last_mining")
    private LocalDateTime lastMining;
    @ManyToOne
    private Empire empire;
    @Column(name = "name")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGoldMiningCapacity() {
        return goldMiningCapacity;
    }

    public void setGoldMiningCapacity(int goldMiningCapacity) {
        this.goldMiningCapacity = goldMiningCapacity;
    }

    public int getIronMiningCapacity() {
        return ironMiningCapacity;
    }

    public void setIronMiningCapacity(int ironMiningCapacity) {
        this.ironMiningCapacity = ironMiningCapacity;
    }

    public int getWoodMiningCapacity() {
        return woodMiningCapacity;
    }

    public void setWoodMiningCapacity(int woodMiningCapacity) {
        this.woodMiningCapacity = woodMiningCapacity;
    }

    public LocalDateTime getLastMining() {
        return lastMining;
    }

    public void setLastMining(LocalDateTime lastMining) {
        this.lastMining = lastMining;
    }

    public Empire getEmpire() {
        return empire;
    }

    public void setEmpire(Empire empire) {
        this.empire = empire;
    }

    public String getName() {
        return name;
    }

    public String getNameWithDefault(String defaultName) {
        return name != null ? name : defaultName;
    }


    public void setName(String name) {
        this.name = name;
    }
}
