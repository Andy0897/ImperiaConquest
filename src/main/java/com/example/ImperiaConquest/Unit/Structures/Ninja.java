package com.example.ImperiaConquest.Unit.Structures;

import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Unit;

public class Ninja extends UnitItem implements UnitInterface {
    public Ninja(Unit unit) {
        super(unit);
    }

    @Override
    public String getName() {
        return "Ninja";
    }

    @Override
    public String getImage() {
        return "/images/ninja.svg";
    }

    @Override
    public String getType() {
        return UnitTypes.NINJA.name();
    }

    @Override
    public Integer getHealth() {
        return 140;
    }

    @Override
    public Integer getAttack() {
        return 110;
    }
}
