package com.bentechapps.konduckitor.model.difficulty.impl;

import com.bentechapps.konduckitor.model.difficulty.Difficulty;

/**
 * Created by Daniel on 2/19/2015.
 */
public class HardDifficulty extends Difficulty {
    @Override
    public int getPointIncreaseDelayTime() {
        return pointIncreaseDelayTime = 1;
    }

    @Override
    public int getMinimumExitTime() {
        return minimumExitTime = 15;
    }
}
