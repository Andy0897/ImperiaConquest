package com.example.ImperiaConquest.Building;

import jakarta.persistence.*;

@Entity
@Table(name = "building_category")
public class BuildingCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String category;
}
