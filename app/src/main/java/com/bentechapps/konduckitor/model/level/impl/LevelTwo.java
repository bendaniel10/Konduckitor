package com.bentechapps.konduckitor.model.level.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.level.Level;

/**
 * Created by Daniel on 5/2/2015.
 */
public class LevelTwo extends Level {
    @Override
    public String getName() {
        return "Victoria Island";
    }

    @Override
    public String getDescription() {
        return "VI isn't as easy as Lekki, but it's not the toughest too. Missions are tougher now.";
    }

    @Override
    public int getLevel() {
        return 2;
    }

    @Override
    public int getDrawable() {
        return R.drawable.victoria_island_level;
    }
}
