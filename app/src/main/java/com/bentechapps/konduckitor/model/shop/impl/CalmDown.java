package com.bentechapps.konduckitor.model.shop.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bentech.android.appcommons.utils.DrawableUtils;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.person.Passenger;
import com.bentechapps.konduckitor.model.shop.ShopItem;

import static com.bentechapps.konduckitor.activity.fragments.GamePlayFragment.TARGET_FPS;

/**
 * Created by BenTech on 2/8/2015.
 */
public class CalmDown extends ShopItem {


    private final ApplicationData appData;

    public CalmDown(Context context) {
        super(context);
        appData = ApplicationData.getInstance();
    }

    @Override
    public int getDuration() {
        return (10 - (Constants.MAX_POWER_UP_UPGRADE_LEVEL - getUpgradeLevel())) * GamePlayFragment.TARGET_FPS;
    }

    @Override
    public String getName() {
        return context.getString(R.string.calm_down);
    }

    @Override
    public int getHave() {
        return appData.getCalmDownCount();
    }

    @Override
    public Drawable getImage() {
        return DrawableUtils.getDrawable(context, R.drawable.calm_down);
    }

    @Override
    public String getDescription() {
        return context.getString(R.string.calm_down_description, getDuration() / TARGET_FPS);
    }

    @Override
    public int getCost() {
        return 1000;
    }

    @Override
    public void execute(final GamePlayFragment gamePlayFragment) {
        if (gamePlayFragment.getGamePlayFragmentData().getPowerUpDuration() == 1) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementCalmDownUseCount(1);
        }

        Passenger passenger;
        for (int j = gamePlayFragment.getPassengersAdapter().getItemCount() - 1; j >= 0; j--) {
            passenger = gamePlayFragment.getPassengersAdapter().getItem(j);

            if (passenger.getTimeLeft() < passenger.getExitTime()) {
                passenger.setTimeLeft((short) (passenger.getTimeLeft() + 1));
            }

            final int finalJ = j;
            gamePlayFragment.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gamePlayFragment.getPassengersAdapter().notifyItemChanged(finalJ);
                }
            });

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

    @Override
    public void incrementUpgradeLevel(int offset) {
        super.incrementUpgradeLevel(offset);
        appData.incrementCalmDownLevel(offset);
    }


    @Override
    public int getUpgradeLevel() {
        return appData.getCalmDownLevel();
    }
}
