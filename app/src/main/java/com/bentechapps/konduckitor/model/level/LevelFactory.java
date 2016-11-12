package com.bentechapps.konduckitor.model.level;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.bentech.android.appcommons.AppCommons;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.app.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bendaniel on 12/11/2016.
 */

public class LevelFactory {


    private static ArrayList<Level> levels;

    public static LevelBuilder builder() {
        return new LevelBuilder();
    }


    public synchronized static List<Level> listLevels() {

        if (levels != null) {
            return levels;
        }

        levels = new ArrayList<>(Constants.MAX_LEVEL);

        LevelBuilder levelBuilder = builder();

        levelBuilder.withDescription(R.string.level_one_description)
                .withName(R.string.level_one_name)
                .withLevel(1)
                .withDrawable(R.drawable.lekki_level);

        levels.add(levelBuilder.build());

        levelBuilder.withDescription(R.string.level_two_description)
                .withName(R.string.level_two_name)
                .withLevel(2)
                .withDrawable(R.drawable.victoria_island_level);

        levels.add(levelBuilder.build());

        levelBuilder.withDescription(R.string.level_three_description)
                .withName(R.string.level_three_name)
                .withLevel(3)
                .withDrawable(R.drawable.cms_level);

        levels.add(levelBuilder.build());

        levelBuilder.withDescription(R.string.level_four_description)
                .withName(R.string.level_four_name)
                .withLevel(4)
                .withDrawable(R.drawable.lekki_level);

        levels.add(levelBuilder.build());

        return levels;
    }

    private static class LevelBuilder {

        private int level;
        @DrawableRes
        private int drawable;
        @StringRes
        private int description;
        private int name;

        LevelBuilder withLevel(int level) {
            this.level = level;
            return this;
        }

        LevelBuilder withDrawable(@DrawableRes int drawable) {
            this.drawable = drawable;
            return this;
        }

        LevelBuilder withDescription(@StringRes int description) {
            this.description = description;
            return this;
        }

        LevelBuilder withName(@StringRes int name) {
            this.name = name;
            return this;
        }

        Level build() {
            final Context context = AppCommons.getApplication();

            return new Level() {
                @Override
                public String getName() {
                    return context.getString(name);
                }

                @Override
                public String getDescription() {
                    return context.getString(description);
                }

                @Override
                public int getLevel() {
                    return level;
                }

                @Override
                public int getDrawable() {
                    return drawable;
                }
            };
        }

    }

}
