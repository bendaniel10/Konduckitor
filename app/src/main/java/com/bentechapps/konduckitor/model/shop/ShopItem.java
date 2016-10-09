package com.bentechapps.konduckitor.model.shop;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.engine.GameLoopItem;
import com.bentechapps.konduckitor.model.shop.impl.CalmDown;
import com.bentechapps.konduckitor.model.shop.impl.Comedian;
import com.bentechapps.konduckitor.model.shop.impl.GraGra;
import com.bentechapps.konduckitor.model.shop.impl.RegenerateHealth;
import com.bentechapps.konduckitor.model.shop.impl.SplitLargestChange;
import com.bentechapps.konduckitor.view.GamePlayPersonTile;

import java.util.Arrays;
import java.util.List;

/**
 * Created by BenTech on 2/8/2015.
 */
public abstract class ShopItem implements GameLoopItem{
    protected Drawable image;
    protected String description;
    protected int cost;
    protected int have;
    protected Context context;
    protected int duration;
    protected String name;

    public abstract int getDuration() ;

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public abstract String getName();

    public void setName(String name) {
        this.name = name;
    }

    private ShopItem() {
    }

    public ShopItem(Context context) {
        this.context = context;
    }

    protected void setImage(Drawable image) {
        this.image = image;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setCost(int cost) {
        this.cost = cost;
    }

    public abstract int getHave();

    protected void setHave(int have) {
        this.have = have;
    }

    public abstract Drawable getImage();
    public abstract String getDescription();
    public abstract int getCost();

    public abstract void execute(GamePlayFragment gamePlayFragment);

    public static List<ShopItem> list(Context context){
        return Arrays.asList( new SplitLargestChange(context), new GraGra(context), new CalmDown(context), new Comedian(context), new RegenerateHealth(context));
    }

    @Override
    public void doGameRender(GamePlayFragment gamePlayFragment) {
        GamePlayFragmentData gamePlayFragmentData = gamePlayFragment.getGamePlayFragmentData();
        if (gamePlayFragmentData.isPowerUpMode()) {
            if (gamePlayFragmentData.getPowerUpDuration() <= gamePlayFragment.getGamePlayTailView().getGamePlayTailData().getDefaultShopItem().getDuration()) {
                gamePlayFragment.getGamePlayTailView().getGamePlayTailData().getDefaultShopItem().execute(gamePlayFragment);
            } else {
                cancelExecution(gamePlayFragment);
            }
        }
    }

    @Override
    public void doGameUpdates(GamePlayFragment gamePlayFragment, double delta) {
        GamePlayFragmentData gamePlayFragmentData = gamePlayFragment.getGamePlayFragmentData();
        if (gamePlayFragmentData.isPowerUpMode()) {
            if ((double)gamePlayFragmentData.getPowerUpDuration() < (double)gamePlayFragment.getGamePlayTailView().getGamePlayTailData().getDefaultShopItem().getDuration() * delta) {
                gamePlayFragmentData.incrementPowerUpDuration(1);
            }
        }
    }

    protected void cancelExecution(GamePlayFragment gamePlayFragment) {
        GamePlayFragmentData gamePlayFragmentData = gamePlayFragment.getGamePlayFragmentData();
        gamePlayFragmentData.setPowerUpDuration(0);
        gamePlayFragmentData.setPowerUpMode(false);
    }

    public abstract int getMinimumAllowedHighScore();
    public void decrementHave(int offset) {
        this.have -= offset;
    }
    public void incrementHave(int offset) {
        this.have += offset;
    }
}
