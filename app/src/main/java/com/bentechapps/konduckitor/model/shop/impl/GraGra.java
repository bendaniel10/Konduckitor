package com.bentechapps.konduckitor.model.shop.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.view.GamePlayPersonTile;

/**
 * Created by Daniel on 7/26/2015.
 */
public class GraGra extends ShopItem {

    private final int graGraOffset = 3;

    private final ApplicationData appData;

    public GraGra(Context context) {
        super(context);
        this.appData = ApplicationData.getInstance(context);
    }

    @Override
    public int getDuration() {
        return 3 * GamePlayFragment.TARGET_FPS;
    }

    @Override
    public String getName() {
        return "Gra Gra";
    }

    @Override
    public int getHave() {
        return appData.getGraGraCount();
    }

    @Override
    public Drawable getImage() {
        return context.getResources().getDrawable(R.drawable.gra_gra);
    }

    @Override
    public String getDescription() {
        return "Speeds things up a bit. settled passengers give 2x more score, angry passengers take 2x more health. This doesn't affect play time.";
    }

    @Override
    public int getCost() {
        return 500;
    }

    @Override
    public void execute(GamePlayFragment gamePlayFragment) {
        if(gamePlayFragment.getGamePlayFragmentData().getPowerUpDuration() == 1) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementGraGraUseCount(1);
            gamePlayFragment.getGamePlayFragmentData().setUnsettledPassengerHealthDeduction(gamePlayFragment.getGamePlayFragmentData().getUnsettledPassengerHealthDeduction() * graGraOffset * 2);
            gamePlayFragment.getGamePlayFragmentData().setSettledPassengerRewardPoint(gamePlayFragment.getGamePlayFragmentData().getSettledPassengerRewardPoint() * graGraOffset * 2);
        }
        for (int j = gamePlayFragment.getGridView().getAdapter().getCount() - 1; j >= 0; j--) {
            GamePlayPersonTile tile = (GamePlayPersonTile) gamePlayFragment.getGridView().getAdapter().getItem(j);
            tile.decrementTimeRemaining((short) graGraOffset);
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
}
