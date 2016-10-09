package com.bentechapps.konduckitor.model.denomination.unit.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;

/**
 * Created by Daniel on 2/5/2015.
 */
public class FivesDenominationUnit extends DenominationUnit {
    public FivesDenominationUnit() {
        super.incrementValue((short) 5);
    }

    @Override
    public short getValue() {
        return super.value;
    }

    @Override
    public int getWalletButtonId() {
        return R.id.fives;
    }
}
