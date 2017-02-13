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
 * Created by Daniel on 7/26/2015.
 */
public class GraGra extends ShopItem {

    private final int graGraOffset = 3;

    private final ApplicationData appData;

    public GraGra(Context context) {
        super(context);
        this.appData = ApplicationData.getInstance();
    }

    @Override
    public int getDuration() {
        return (7 -  (Constants.MAX_POWER_UP_UPGRADE_LEVEL - getUpgradeLevel()))  * TARGET_FPS;
    }

    @Override
    public String getName() {
        return context.getString(R.string.gra_gra);
    }

    @Override
    public int getHave() {
        return appData.getGraGraCount();
    }

    @Override
    public Drawable getImage() {
        return DrawableUtils.getDrawable(context, R.drawable.gra_gra);
    }

    @Override
    public String getDescription() {
        return context.getString(R.string.gra_gra_description, getDuration() / TARGET_FPS);
    }

    @Override
    public int getCost() {
        return 500;
    }

    @Override
    public void execute(final GamePlayFragment gamePlayFragment) {
        if (gamePlayFragment.getGamePlayFragmentData().getPowerUpDuration() == 1) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementGraGraUseCount(1);
            gamePlayFragment.getGamePlayFragmentData().setUnsettledPassengerHealthDeduction(gamePlayFragment.getGamePlayFragmentData().getUnsettledPassengerHealthDeduction() * graGraOffset * 2);
            gamePlayFragment.getGamePlayFragmentData().setSettledPassengerRewardPoint(gamePlayFragment.getGamePlayFragmentData().getSettledPassengerRewardPoint() * graGraOffset * 2);
        }

        Passenger passenger;
        for (int j = gamePlayFragment.getPassengersAdapter().getItemCount() - 1; j >= 0; j--) {
            passenger = gamePlayFragment.getPassengersAdapter().getItem(j);

            if (passenger.getTimeLeft() < passenger.getExitTime()) {
                passenger.setTimeLeft((short) (passenger.getTimeLeft() - graGraOffset));
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
        return 2000;
    }

    @Override
    public void decrementHave(int offset) {
        super.decrementHave(offset);
        appData.decrementGraGraCount(offset);
    }

    @Override
    public void incrementHave(int offset) {
        super.incrementHave(offset);
        appData.incrementGraGraCount(offset);
    }

    @Override
    protected void cancelExecution(GamePlayFragment gamePlayFragment) {
        super.cancelExecution(gamePlayFragment);
        gamePlayFragment.getGamePlayFragmentData().setUnsettledPassengerHealthDeduction(gamePlayFragment.getGamePlayFragmentData().getUnsettledPassengerHealthDeduction() / (graGraOffset * 2));
        gamePlayFragment.getGamePlayFragmentData().setSettledPassengerRewardPoint(gamePlayFragment.getGamePlayFragmentData().getSettledPassengerRewardPoint() / (graGraOffset * 2));
    }


    @Override
    public void incrementUpgradeLevel(int offset) {
        super.incrementUpgradeLevel(offset);
        appData.incrementGraGraLevel(offset);
    }

    @Override
    public int getUpgradeLevel() {
        return appData.getGraGraLevel();
    }
}
