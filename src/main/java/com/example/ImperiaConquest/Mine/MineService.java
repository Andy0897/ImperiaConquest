package com.example.ImperiaConquest.Mine;

import org.springframework.stereotype.Service;

@Service
public class MineService {
    public Mine setUpMine(Mine mine) {
        mine.setMiningCapacity(100);
        return mine;
    }
}
