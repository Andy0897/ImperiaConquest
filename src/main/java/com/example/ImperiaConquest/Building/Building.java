package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    @ManyToOne
    @JoinColumn(name = "empire_id")
    private Empire empire;
    @Column(name = "collected_at")
    private LocalDateTime collected_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Empire getEmpire() {
        return empire;
    }

    public void setEmpire(Empire empire) {
        this.empire = empire;
    }

    public LocalDateTime getCollected_at() {
        return collected_at;
    }

    public void setCollected_at(LocalDateTime collected_at) {
        this.collected_at = collected_at;
    }
}