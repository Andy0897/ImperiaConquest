package com.example.ImperiaConquest.ResourceBuilding;

import com.example.ImperiaConquest.Empire.Empire;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "resource_buildings")
public class ResourceBuilding {
    @Column(name = "resource_building_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "mining_capacity")
    private int miningCapacity;
    @Column(name = "last_mining")
    private LocalDateTime lastMining;
    @ManyToOne
    @JoinColumn(name = "empire_id")
    private Empire empire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMiningCapacity() {
        return miningCapacity;
    }

    public void setMiningCapacity(int miningCapacity) {
        this.miningCapacity = miningCapacity;
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
}