package com.bentechapps.konduckitor.model.denomination.impl;

import android.content.Context;

import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.denomination.Denomination;
import com.bentechapps.konduckitor.model.denomination.unit.impl.FiftiesDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.FivesDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.HundredsDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.TensDenominationUnit;
import com.bentechapps.konduckitor.model.denomination.unit.impl.TwentiesDenominationUnit;

import java.util.Random;

/**
 * Created by Daniel on 2/6/2015.
 */
public class DefaultConductorWalletDenomination extends Denomination {

    private ApplicationData appData;
    private Random random;
    private int MINIMUM_EASY_COUNT = 15;
    private int MINIMUM_NORMAL_COUNT = 20;
    private int MINIMUM_HARD_COUNT = 15;
    private int MINIMUM_COUNT = MINIMUM_NORMAL_COUNT;

    private DefaultConductorWalletDenomination() {
    }

    public DefaultConductorWalletDenomination(Context context) {
        random = new Random();
        appData = ApplicationData.getInstance();
        handleDifficulty();

        fives = new FivesDenominationUnit();
        fives.incrementCount((short) ((short) random.nextInt(MINIMUM_COUNT) + MINIMUM_COUNT));
        tens = new TensDenominationUnit();
        tens.incrementCount((short) ((short) random.nextInt(MINIMUM_COUNT) + MINIMUM_COUNT));
        twenties = new TwentiesDenominationUnit();
        twenties.incrementCount((short) ((short) random.nextInt(MINIMUM_COUNT) + MINIMUM_COUNT));
        fifties = new FiftiesDenominationUnit();
        fifties.incrementCount((short) ((short) random.nextInt(MINIMUM_COUNT) + MINIMUM_COUNT));
        hundreds = new HundredsDenominationUnit();
        hundreds.incrementCount((short) ((short) random.nextInt(MINIMUM_COUNT) + MINIMUM_COUNT));
    }

    private void handleDifficulty() {
        switch (appData.getDifficulty()) {
            case 0:
                MINIMUM_COUNT = MINIMUM_EASY_COUNT;
                break;
            case 1:
                MINIMUM_COUNT = MINIMUM_NORMAL_COUNT;
                break;
            case 2:
                MINIMUM_COUNT = MINIMUM_HARD_COUNT;
                break;
        }
    }
}
