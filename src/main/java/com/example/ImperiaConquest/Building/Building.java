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

}