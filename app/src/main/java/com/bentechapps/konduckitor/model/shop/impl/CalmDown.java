package com.bentechapps.konduckitor.model.shop.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.view.GamePlayPersonTile;

/**
 * Created by BenTech on 2/8/2015.
 */
public class CalmDown extends ShopItem {


    private final ApplicationData appData;

    public CalmDown(Context context) {
        super(context);
        appData = ApplicationData.getInstance(context);
    }

    @Override
    public int getDuration() {
        return 10 * GamePlayFragment.TARGET_FPS;
    }

    @Override
    public String getName() {
        return "Calm Down";
    }

    @Override
    public int getHave() {
        return appData.getCalmDownCount();
    }

    @Override
    public Drawable getImage() {
        return context.getResources().getDrawable(R.drawable.calm_down);
    }

    @Override
    public String getDescription() {
        return "Prevents the passenger's patience from reducing. Driver will encounter road traffic for a limited time. " +
                "Be careful, those who have run out of patience will still deplete your health.";
    }

    @Override
    public int getCost() {
        return 1000;
    }

    @Override
    public void execute(GamePlayFragment gamePlayFragment) {
        if (gamePlayFragment.getGamePlayFragmentData().getPowerUpDuration() == 1) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementCalmDownUseCount(1);
        }
        for (int j = gamePlayFragment.getPersonTileAdapter().getCount() - 1; j >= 0; j--) {
            GamePlayPersonTile tile = (GamePlayPersonTile) gamePlayFragment.getPersonTileAdapter().getItem(j);
            tile.incrementTimeRemaining((short) 1);
        }
    }

    @Override
    public int getMinimumAllowedHighScore() {
        return 3000;
    }

    @Override
    public void decrementHave(int offset) {
        super.decrementHave(offset);
        appData.decrementCalmDownCount(offset);
    }

    @Override
    public void incrementHave(int offset) {
        super.incrementHave(offset);
        appData.incrementCalmDownCount(offset);
    }
}
