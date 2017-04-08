package com.bentechapps.konduckitor.model.shop.impl;

import android.widget.Toast;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.shop.NonPowerUpItem;

/**
 * Created by bendaniel on 25/02/2017.
 */

public class BigWalletItem extends NonPowerUpItem {
    @Override
    public int getDrawableId() {
        return R.drawable.star;
    }

    @Override
    public int getDescriptionResId() {
        return R.string.big_wallet_description;
    }

    @Override
    public int getCost() {
        return 10000;
    }

    @Override
    public int getHave() {
        return appData.getBigWalletItemCount();
    }

    @Override
    public int getNameResId() {
        return R.string.big_wallet;
    }

    @Override
    public void purchase() {

        if(appData.getReputation() < getCost()) {
            Toast.makeText(
                    context,
                    R.string.you_do_not_have_enough_money,
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        appData.decrementReputation(getCost());
        appData.incrementBigWalletItemCount(1);
        appData.savePreference();
    }
}
