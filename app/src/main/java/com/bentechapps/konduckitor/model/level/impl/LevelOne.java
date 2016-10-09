package com.bentechapps.konduckitor.model.level.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.level.Level;

/**
 * Created by Daniel on 5/2/2015.
 */
public class LevelOne extends Level {
    @Override
    public String getName() {
        return "Lekki";
    }

    @Override
    public String getDescription() {
        return "This is Lekki, things aren't as tough as they ought to be. You'll encounter a lot of soft mission here.";
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getDrawable() {
        return R.drawable.lekki_level;
    }

}
