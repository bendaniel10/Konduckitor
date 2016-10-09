package com.bentechapps.konduckitor.model.shop.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.ShopItem;

/**
 * Created by BenTech on 2/8/2015.
 */
public class RegenerateHealth extends ShopItem {


    private final ApplicationData appData;

    public RegenerateHealth(Context context) {
        super(context);
        appData = ApplicationData.getInstance(context);
    }

    @Override
    public int getDuration() {
        return 10 * GamePlayFragment.TARGET_FPS;
    }

    @Override
    public String getName() {
        return "Regenerate";
    }

    @Override
    public int getHave() {
        return appData.getRegenerateCount();
    }

    @Override
    public Drawable getImage() {
        return context.getResources().getDrawable(R.drawable.regenerate_life);
    }

    @Override
    public String getDescription() {
        return "Causes driver to drive slowly. It regenerates your health for a limited time interval.";
    }

    @Override
    public int getCost() {
        return 5000;
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
}
