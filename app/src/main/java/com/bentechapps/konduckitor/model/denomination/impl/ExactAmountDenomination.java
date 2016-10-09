package com.bentechapps.konduckitor.model.denomination.impl;

import com.bentechapps.konduckitor.model.denomination.Denomination;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;

/**
 * Created by BenTech on 1/31/2015.
 */
public class ExactAmountDenomination extends Denomination {
    private final DenominationUnit denominationUnit;

    private ExactAmountDenomination() {
        denominationUnit = null;
    }

    private ExactAmountDenomination(DenominationUnit denominationUnit) {
        this.denominationUnit = denominationUnit;
    }

    public static ExactAmountDenomination newExactDenomination(DenominationUnit denominationUnit) {
        return new ExactAmountDenomination(denominationUnit);
    }
}
