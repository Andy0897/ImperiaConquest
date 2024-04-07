
package com.example.ImperiaConquest.Building;

import com.example.ImperiaConquest.Empire.Empire;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "buildings",
    uniqueConstraints = {@UniqueConstraint(
    columnNames = {"empire_id", "type"}
)}
)
public class Building {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private Long id;
    private String type;
    private Long level;
    @ManyToOne
    @JoinColumn(
        name = "empire_id"
    )
    private Empire empire;
    @Column(
        name = "collected_at"
    )
    private LocalDateTime collected_at;

    public Building() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Empire getEmpire() {
        return this.empire;
    }

    public void setEmpire(Empire empire) {
        this.empire = empire;
    }

    public LocalDateTime getCollected_at() {
        return this.collected_at;
    }

    public void setCollected_at(LocalDateTime collected_at) {
        this.collected_at = collected_at;
    }

    public Long getLevel() {
        return this.level != null ? this.level : 0L;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
