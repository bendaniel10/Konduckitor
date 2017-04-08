package com.bentechapps.konduckitor.model.shop;

import android.content.Context;
import android.support.annotation.StringRes;

import com.bentech.android.appcommons.AppCommons;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.impl.BigWalletItem;
import com.bentechapps.konduckitor.model.shop.impl.ExtraPowerUpSlotItem;
import com.bentechapps.konduckitor.model.shop.impl.GoldenStarterPack;
import com.bentechapps.konduckitor.model.shop.impl.SilverStarterPack;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bendaniel on 25/02/2017.
 */

public abstract class NonPowerUpItem {
    public static final int UNLIMITED_HAVES = -1;
    protected final ApplicationData appData;
    protected final Context context;
    protected int have;
    @StringRes
    protected int nameResId;

    public NonPowerUpItem() {
        this.context = AppCommons.getApplication();
        this.appData = ApplicationData.getInstance();
    }

    public static List<NonPowerUpItem> list() {
        return Arrays.asList(
                new ExtraPowerUpSlotItem(),
                new BigWalletItem(),
                new SilverStarterPack(),
                new GoldenStarterPack()
        );
    }

    public abstract int getDrawableId();

    public abstract int getDescriptionResId();

    public abstract int getCost();

    public abstract int getHave();

    public abstract int getNameResId();

    public abstract void purchase();
}
