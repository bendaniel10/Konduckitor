package com.bentechapps.konduckitor.model.denomination.unit;

import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.model.denomination.unit.impl.FiftiesDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.FivesDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.HundredsDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.TensDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.TwentiesDenominationUnit;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by BenTech on 1/31/2015.
 */
public abstract class DenominationUnit implements Comparable<DenominationUnit> {
    protected short value;
    private short count;

    public static List<DenominationUnit> list() {
        return Arrays.asList(new FivesDenominationUnit(), new TensDenominationUnit(),
                new TwentiesDenominationUnit(), new FiftiesDenominationUnit(),
                new HundredsDenominationUnit());
    }

    public static DenominationUnit getRandomDenominationUnit(DenominationUnit minDenominationUnit) {
        DenominationUnit d = getRandomDenominationUnit();
        if (d.compareTo(minDenominationUnit) < 0) {
            return getRandomDenominationUnit(minDenominationUnit);
        }
        return d;
    }

    public static DenominationUnit getRandomDenominationUnit() {
        DenominationUnit d = new HundredsDenominationUnit();
        d.incrementCount((short) 1);
        switch (new Random().nextInt(Constants.MAX_DENOMINATIONS)) {
            case 0:
                d = new FivesDenominationUnit();
                d.incrementCount((short) 1);
                break;
            case 1:
                d = new TensDenominationUnit();
                d.incrementCount((short) 1);
                break;
            case 2:
                d = new TwentiesDenominationUnit();
                d.incrementCount((short) 1);
                break;
            case 3:
                d = new FiftiesDenominationUnit();
                d.incrementCount((short) 1);
                break;
            case 4:
                d = new HundredsDenominationUnit();
                d.incrementCount((short) 1);
                break;

        }
        return d;
    }

    public abstract short getValue();

    public short getCount() {
        return count;
    }

    public short incrementCount(short offset) {
        return this.count = (short) (getCount() + offset);
    }

    public short decrementCount(short offset) {
        return this.count = (short) (getCount() - offset);
    }

    public short incrementValue(short offset) {
        return this.value = (short) (getValue() + offset);
    }

    public short decrementValue(short offset) {
        return this.value = (short) (getValue() - offset);
    }

    public short getTotalValue() {
        return (short) (getCount() * getValue());
    }

    public abstract int getWalletButtonId();

    @Override
    public String toString() {
        return new StringBuilder(String.valueOf(getValue())).append(", ").append(getCount()).toString();
    }

    @Override
    public int compareTo(DenominationUnit another) {
        return this.getValue() < another.getValue() ? -1 : this.getValue() == another.getValue() ? 0 : 1;
    }

    public boolean isEmpty() {
        return getCount() == 0;
    }
}
