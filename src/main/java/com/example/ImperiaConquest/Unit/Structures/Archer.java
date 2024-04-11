package com.example.ImperiaConquest.Unit.Structures;

import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Unit;

public class Archer extends UnitItem implements UnitInterface {

    public Archer(Unit unit) {
        super(unit);
    }

    @Override
    public String getName() {
        return "Archer";
    }

    @Override
    public String getImage() {
        return "/images/archer.svg";
    }

    @Override
    public String getType() {
        return UnitTypes.ARCHER.name();
    }

    @Override
    public Integer getHealth() {
        return 100;
    }

    @Override
    public Integer getAttack() {
        return 100;
    }
}
