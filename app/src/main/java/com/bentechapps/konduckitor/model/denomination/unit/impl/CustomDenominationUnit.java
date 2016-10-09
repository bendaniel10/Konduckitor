package com.bentechapps.konduckitor.model.denomination.unit.impl;

import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;

/**
 * Created by Daniel on 2/6/2015.
 */
public class CustomDenominationUnit extends DenominationUnit {

    private CustomDenominationUnit(){value = 0;}

    public CustomDenominationUnit(short value) {
        super.value = value;
    }
    @Override
    public short getValue() {
        return value;
    }

    @Override
    public int getWalletButtonId() {
        return 0;
    }
}
