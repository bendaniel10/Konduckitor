package com.bentechapps.konduckitor.model.shop.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.view.GamePlayPersonTile;

/**
 * Created by BenTech on 2/9/2015.
 */
public class Comedian extends ShopItem {
    private final ApplicationData appData;

    public Comedian(Context context) {
        super(context);
        this.appData = ApplicationData.getInstance(context);
    }

    @Override
    public int getDuration() {
        return 5 * GamePlayFragment.TARGET_FPS;
    }

    @Override
    public String getName() {
        return "Comedian";
    }

    @Override
    public int getHave() {
        return appData.getComedianCount();
    }

    @Override
    public Drawable getImage() {
        return context.getResources().getDrawable(R.drawable.comedian);
    }

    @Override
    public String getDescription() {
        return "Crack some jokes. This increases the patience of all your passengers for a limited time.";
    }

    @Override
    public int getCost() {
        return 2500;
    }

    @Override
    public void execute(GamePlayFragment gamePlayFragment) {
        if(gamePlayFragment.getGamePlayFragmentData().getPowerUpDuration() == 1) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementComedianUseCount(1);
        }
        for (int j = gamePlayFragment.getPersonTileAdapter().getCount() - 1; j >= 0; j--) {
            GamePlayPersonTile tile = (GamePlayPersonTile) gamePlayFragment.getPersonTileAdapter().getItem(j);
            tile.incrementTimeRemaining((short) 3);
        }
    }

    @Override
    public int getMinimumAllowedHighScore() {
        return 7000;
    }

    @Override
    public void decrementHave(int offset) {
        super.decrementHave(offset);
        appData.decrementComedianCount(offset);
    }

    @Override
    public void incrementHave(int offset) {
        super.incrementHave(offset);
        appData.incrementComedianCount(offset);
    }
}
