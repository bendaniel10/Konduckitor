package com.bentechapps.konduckitor.model.difficulty;

import com.bentechapps.konduckitor.model.difficulty.impl.EasyDifficulty;
import com.bentechapps.konduckitor.model.difficulty.impl.HardDifficulty;
import com.bentechapps.konduckitor.model.difficulty.impl.NormalDifficulty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel on 2/19/2015.
 */
public abstract class Difficulty {

    protected int pointIncreaseDelayTime;
    protected int minimumExitTime;

    public abstract int getPointIncreaseDelayTime();

    protected void setPointIncreaseDelayTime(int pointIncreaseDelayTime) {
        this.pointIncreaseDelayTime = pointIncreaseDelayTime;
    }

    public abstract int getMinimumExitTime();

    protected void setMinimumExitTime(int minimumExitTime) {
        this.minimumExitTime = minimumExitTime;
    }

    public static List<Difficulty> list() {
        return Arrays.asList(new EasyDifficulty(), new NormalDifficulty(), new HardDifficulty());
    }
}
