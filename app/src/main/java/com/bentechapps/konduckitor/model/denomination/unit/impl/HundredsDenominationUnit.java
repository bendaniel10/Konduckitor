package com.bentechapps.konduckitor.model.denomination.unit.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;

/**
 * Created by BenTech on 1/31/2015.
 */
public class HundredsDenominationUnit extends DenominationUnit {
    public HundredsDenominationUnit() {
        super.incrementValue((short) 100);
    }

    @Override
    public short getValue() {
        return super.value;
    }

    @Override
    public int getWalletButtonId() {
        return R.id.hundreds;
    }
}
