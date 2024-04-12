package com.example.ImperiaConquest.Enums;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;

public enum UnitTypes {
    ARCHER,
    KNIGHT,
    CATAPULT,
    PIRATE,
    NINJA,
    VIKING;

    public static boolean contains(String type) {
        return ObjectUtils.containsConstant(values(), type, false);
    }
}
