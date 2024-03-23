package com.example.ImperiaConquest.Empire;

import com.example.ImperiaConquest.User.User;
import jakarta.persistence.*;

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

}
