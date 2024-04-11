package com.example.ImperiaConquest.Unit.Structures;

import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Unit;

public class Catapult extends UnitItem implements UnitInterface {
    public Catapult(Unit unit) {
        super(unit);
    }

    @Override
    public String getName() {
        return "Catapult";
    }

    @Override
    public String getImage() {
        return "/images/catapult.svg";
    }

    @Override
    public String getType() {
        return UnitTypes.CATAPULT.name();
    }

    @Override
    public Integer getHealth() {
        return 300;
    }

    @Override
    public Integer getAttack() {
        return 30;
    }
}
