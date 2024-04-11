package com.example.ImperiaConquest.Unit.Structures;

import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Unit;

public class Pirate extends UnitItem implements UnitInterface {
    public Pirate(Unit unit) {
        super(unit);
    }

    @Override
    public String getName() {
        return "Pirate";
    }

    @Override
    public String getImage() {
        return "/images/pirate.svg";
    }

    @Override
    public String getType() {
        return UnitTypes.PIRATE.name();
    }

    @Override
    public Integer getHealth() {
        return 50;
    }

    @Override
    public Integer getAttack() {
        return 140;
    }
}
