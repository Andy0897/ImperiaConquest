package com.example.ImperiaConquest.Unit.Structures;

import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Unit;

public class Viking extends UnitItem implements UnitInterface {
    public Viking(Unit unit) {
        super(unit);
    }

    @Override
    public String getName() {
        return "Viking";
    }

    @Override
    public String getImage() {
        return "/images/viking.svg";
    }

    @Override
    public String getType() {
        return UnitTypes.VIKING.name();
    }

    @Override
    public Integer getHealth() {
        return 180;
    }

    @Override
    public Integer getAttack() {
        return 90;
    }
}
