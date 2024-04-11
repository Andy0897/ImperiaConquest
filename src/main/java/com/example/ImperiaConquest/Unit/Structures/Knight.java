package com.example.ImperiaConquest.Unit.Structures;

import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Unit;

public class Knight extends UnitItem implements UnitInterface {
    public Knight(Unit unit) {
        super(unit);
    }

    @Override
    public String getName() {
        return "Knight";
    }

    @Override
    public String getImage() {
        return "/images/knight.svg";
    }

    @Override
    public String getType() {
        return UnitTypes.KNIGHT.name();
    }

    @Override
    public Integer getHealth() {
        return 150;
    }

    @Override
    public Integer getAttack() {
        return 90;
    }
}
