package com.example.ImperiaConquest.Unit;

import com.example.ImperiaConquest.Building.Building;
import com.example.ImperiaConquest.Empire.Empire;
import jakarta.persistence.*;

@Entity
@Table(
        name = "units",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"empire_id", "type"}
        )}
)
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private int count;

    @ManyToOne
    @JoinColumn(name = "empire_id")
    private Empire empire;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public Unit() {
    }

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
