package com.bentechapps.konduckitor.model.shop.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bentech.android.appcommons.utils.DrawableUtils;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.ShopItem;

import static com.bentechapps.konduckitor.activity.fragments.GamePlayFragment.TARGET_FPS;

/**
 * Created by BenTech on 2/8/2015.
 */
public class RegenerateHealth extends ShopItem {


    private final ApplicationData appData;

    public RegenerateHealth(Context context) {
        super(context);
        appData = ApplicationData.getInstance();
    }

    @Override
    public int getDuration() {
        return (10 - (Constants.MAX_POWER_UP_UPGRADE_LEVEL - getUpgradeLevel())) * TARGET_FPS;
    }

    @Override
    public String getName() {
        return context.getString(R.string.regenerate);
    }

    @Override
    public int getHave() {
        return appData.getRegenerateCount();
    }

    @Override
    public Drawable getImage() {
        return DrawableUtils.getDrawable(context, R.drawable.regenerate_life);
    }

    @Override
    public String getDescription() {
        return context.getString(R.string.regenerate_health_description, getDuration() / TARGET_FPS);
    }

    @Override
    public int getCost() {
        return 3000;
    }

    @Override
    public void execute(final GamePlayFragment gamePlayFragment) {
        if (gamePlayFragment.getGamePlayFragmentData().getPowerUpDuration() == 1) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementRegenerateUseCount(1);
        }
        gamePlayFragment.getGamePlayHeaderView().incrementLife(5);
    }

    @Override
    public int getMinimumAllowedHighScore() {
        return 10000;
    }

    @Override
    public void decrementHave(int offset) {
        super.decrementHave(offset);
        appData.decrementRegenerateCount(offset);
    }

    @Override
    public void incrementHave(int offset) {
        super.incrementHave(offset);
        appData.incrementRegenerateCount(offset);
    }

    @Override
    public void incrementUpgradeLevel(int offset) {
        super.incrementUpgradeLevel(offset);
        appData.incrementRegenerateLevel(offset);
    }

    @Override
    public int getUpgradeLevel() {
        return appData.getRegenerateLevel();
    }
}
