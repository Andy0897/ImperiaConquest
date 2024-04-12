package com.example.ImperiaConquest.Unit.Structures;

import com.example.ImperiaConquest.Enums.UnitTypes;
import com.example.ImperiaConquest.Interfaces.UnitInterface;
import com.example.ImperiaConquest.Unit.Unit;

public class Blank  extends UnitItem implements UnitInterface {

    public Blank(Unit unit) {
        super(unit);
    }

    @Override
    public String getName() {
        return "Blank";
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
        return 0;
    }

    @Override
    public Integer getAttack() {
        return 0;
    }
}
