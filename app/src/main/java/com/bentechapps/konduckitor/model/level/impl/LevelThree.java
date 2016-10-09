package com.bentechapps.konduckitor.model.level.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.level.Level;

/**
 * Created by Daniel on 5/2/2015.
 */
public class LevelThree extends Level {
    @Override
    public String getName() {
        return "CMS";
    }

    @Override
    public String getDescription() {
        return "CMS is tough, that is what I can tell you. You need to be a real 'agbero' to scale through";
    }

    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    public int getDrawable() {
        return R.drawable.cms_level;
    }
}
