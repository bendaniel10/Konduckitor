package com.bentechapps.konduckitor.data;

import com.bentechapps.konduckitor.model.denomination.Denomination;
import com.bentechapps.konduckitor.model.shop.ShopItem;

/**
 * Created by Daniel on 2/6/2015.
 */
public class GamePlayTailData {
    private Denomination conductorWallet;
    private ShopItem defaultShopItem;
    private int selectedAmount;

    public ShopItem getDefaultShopItem() {
        return defaultShopItem;
    }

    public void setDefaultShopItem(ShopItem defaultShopItem) {
        this.defaultShopItem = defaultShopItem;
    }

    private GamePlayTailData() {
    }

    private GamePlayTailData(Denomination conductorWallet, int selectedAmount, ShopItem defaultShopItem) {
        this.conductorWallet = conductorWallet;
        this.selectedAmount = selectedAmount;
        this.defaultShopItem = defaultShopItem;
    }

    public static GamePlayTailData newGamePlayTailData(Denomination conductorWallet, int selectedAmount, ShopItem defaultShopItem) {
        return new GamePlayTailData(conductorWallet, selectedAmount, defaultShopItem);
    }

    public int getSelectedAmount() {
        return selectedAmount;
    }

    protected void setSelectedAmount(int selectedAmount) {
        this.selectedAmount = selectedAmount;
    }


    public Denomination getConductorWallet() {
        return conductorWallet;
    }

    protected void setConductorWallet(Denomination conductorWallet) {
        this.conductorWallet = conductorWallet;
    }

    public int incrementSelectedAmount(int offset) {//+=
        return selectedAmount = selectedAmount + offset;
    }

    public int decrementSelectedAmount(int offset) {
        return selectedAmount = selectedAmount - offset;
    }

    public int clearSelectedAmount() {
        return selectedAmount = 0;
    }
}
