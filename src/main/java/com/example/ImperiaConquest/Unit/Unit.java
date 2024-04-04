package com.example.ImperiaConquest.Unit;

import com.example.ImperiaConquest.Empire.Empire;
import jakarta.persistence.*;

@Entity
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private int attack;
    private int health;
    private int defending;
    private int attacking;
    @ManyToOne
    @JoinColumn(name = "empire_id")
    private Empire empire;


}
