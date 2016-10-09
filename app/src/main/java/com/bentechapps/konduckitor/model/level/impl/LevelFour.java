package com.bentechapps.konduckitor.model.level.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.level.Level;

/**
 * Created by Daniel on 5/2/2015.
 */
public class LevelFour extends Level {
    @Override
    public String getName() {
        return "Ikeja";
    }

    @Override
    public String getDescription() {
        return "If you thought that you've seen tough, you must be joking. Welcome to Ikeja, only those from the streets can scale through.";
    }

    @Override
    public int getLevel() {
        return 4;
    }

    @Override
    public int getDrawable() {
        return R.drawable.ikeja_level;
    }
}
