package com.bentechapps.konduckitor.model.shop.impl;

import android.widget.Toast;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.shop.NonPowerUpItem;
import com.bentechapps.konduckitor.model.shop.ShopItem;

import java.util.List;

/**
 * Created by bendaniel on 25/02/2017.
 */

public class GoldenStarterPack extends NonPowerUpItem {
    @Override
    public int getDrawableId() {
        return R.drawable.star;
    }

    @Override
    public int getDescriptionResId() {
        return R.string.gold_starter_pack_description;
    }

    @Override
    public int getCost() {
        return 50000;
    }

    @Override
    public int getHave() {
        return UNLIMITED_HAVES;
    }

    @Override
    public int getNameResId() {
        return R.string.gold_starter_pack;
    }

    @Override
    public void purchase() {


        if (appData.getReputation() < getCost()) {
            Toast.makeText(
                    context,
                    R.string.you_do_not_have_enough_money,
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        List<ShopItem> shopItems = ShopItem.list(context);
        appData.decrementReputation(getCost());
        for (ShopItem s : shopItems) {
            s.incrementHave(10);
        }
    }
}
