package com.bentechapps.konduckitor.model.shop.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.shop.ShopItem;

/**
 * Created by BenTech on 2/8/2015.
 */
@Deprecated
public class AssistantConductor extends ShopItem {

    public AssistantConductor(Context context) {
        super(context);
    }

    @Override
    public int getUpgradeLevel() {
        return 0;
    }

    @Override
    public int getDuration() {
        return 30;
    }



    @Override
    public String getName() {
        return "Assistant";
    }

    @Override
    public int getHave() {
        return 0;
    }

    @Override
    public Drawable getImage() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Hire an assistant. This assistant will ensure that no passenger is" +
                " left waiting for balance. Your health will not be depleted. It runs for a limited time";
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public void execute(GamePlayFragment personTile) {

    }

    @Override
    public int getMinimumAllowedHighScore() {
        return 50000;
    }

    @Override
    public void decrementHave(int offset) {

    }

    @Override
    public void incrementHave(int offset) {

    }
}
