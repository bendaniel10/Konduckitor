package com.bentechapps.konduckitor.model.denomination;

import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.FiftiesDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.FivesDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.HundredsDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.TensDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.TwentiesDenominationUnit;

/**
 * Created by BenTech on 1/31/2015.
 */
public abstract class Denomination {
    protected DenominationUnit tens;
    protected DenominationUnit twenties;
    protected DenominationUnit fifties;
    protected DenominationUnit hundreds;
    protected DenominationUnit fives;

    protected Denomination() {
        this.setFives(new FivesDenominationUnit());
        this.setTens(new TensDenominationUnit());
        this.twenties = new TwentiesDenominationUnit();
        this.setFifties(new FiftiesDenominationUnit());
        this.setHundreds(new HundredsDenominationUnit());
    }

    protected Denomination(DenominationUnit fives, DenominationUnit tens, DenominationUnit twenties, DenominationUnit fifties, DenominationUnit hundreds, DenominationUnit twoHundreds, DenominationUnit fiveHundreds, DenominationUnit oneThousand) {
        this.setTens(tens);
        this.twenties = twenties;
        this.setFifties(fifties);
        this.setHundreds(hundreds);
        this.setFives(fives);
    }

    public int getSumOfDenominationWith() {
        return (short) (getTens().getTotalValue() + getTwenties().getTotalValue() +
                getFifties().getTotalValue() + getHundreds().getTotalValue());
    }


    public DenominationUnit getTens() {
        return tens;
    }

    protected void setTens(DenominationUnit tens) {
        this.tens = tens;
    }

    public DenominationUnit getTwenties() {
        return twenties;
    }

    protected void setTwenties(DenominationUnit twenties) {
        this.twenties = twenties;
    }

    public DenominationUnit getFifties() {
        return fifties;
    }

    protected void setFifties(DenominationUnit fifties) {
        this.fifties = fifties;
    }

    public DenominationUnit getHundreds() {
        return hundreds;
    }

    protected void setHundreds(DenominationUnit hundreds) {
        this.hundreds = hundreds;
    }

    public DenominationUnit getFives() {
        return fives;
    }

    protected void setFives(DenominationUnit fives) {
        this.fives = fives;
    }


    @Override
    public String toString() {
        return new StringBuilder(getTens().toString()).append(twenties.toString()).append(fifties.toString())
                .append(hundreds.toString()).toString();
    }
}
