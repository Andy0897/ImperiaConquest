package com.example.ImperiaConquest.Unit.Structures;

import com.example.ImperiaConquest.Unit.Unit;
import com.example.ImperiaConquest.Unit.UnitService;

public class UnitItem {

    protected Unit unit;

    public UnitItem(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    public Integer getCount() {
        return this.unit.getCount();
    }

    public Integer getHealth() {
        return 0;
    }

    public Integer getAttack() {
        return 0;
    }

    public void reduceUnit(Integer number, UnitService unit) {
        this.unit.setCount(this.unit.getCount() - number);
        unit.getUnitRepository().save(this.unit);
    }
}
