package com.bentechapps.konduckitor.model.shop.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.view.GamePlayPersonTile;
import com.bentechapps.konduckitor.view.GamePlayTailView;
import com.bentechapps.konduckitor.view.custom.WalletButton;

/**
 * Created by Daniel on 2/10/2015.
 */
public class SplitLargestChange extends ShopItem {
    private final ApplicationData appData;

    public SplitLargestChange(Context context) {
        super(context);
        appData = ApplicationData.getInstance(context);
    }

    @Override
    public int getDuration() {
        return 1 * GamePlayFragment.TARGET_FPS;
    }

    @Override
    public String getName() {
        return "Split";
    }

    @Override
    public int getHave() {
        return appData.getSplitLargestChangeCount();
    }

    @Override
    public Drawable getImage() {
        return context.getResources().getDrawable(android.R.drawable.star_big_on);
    }

    @Override
    public String getDescription() {
        return new StringBuilder().append("Driver finds some change at the Petrol Station. ")
                .append("Splits all the money you have in the largest denomination across other smaller")
                .append(" denominations. Your passengers will definitely be angry for the time wasted.").toString();
    }

    @Override
    public int getCost() {
        return 1500;
    }

    @Override
    public void execute(GamePlayFragment gamePlayFragment) {
        if(gamePlayFragment.getGamePlayFragmentData().getPowerUpDuration() == 1) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementSplitUseCount(1);
        }
        DenominationUnit largestDenomination = gamePlayFragment.getGamePlayTailView().getGamePlayTailData().getConductorWallet().getHundreds();
        GamePlayTailView tailView = gamePlayFragment.getGamePlayTailView();
        DenominationUnit split;
        int splitIndex;
        int largestCount = largestDenomination.getCount();

        for(int i = 0; i < largestCount; i++) {//for each highest denomination
            tailView.decrementDenominationUnitCount((WalletButton) tailView.findViewById(R.id.hundreds), largestDenomination, (short) 1);
            splitIndex = DenominationUnit.list().size() - 2;
            for(int whatRemains = largestDenomination.getValue(); whatRemains > 0; ) {//while a largest denomination still has money
                splitIndex = splitIndex < 0 ? DenominationUnit.list().size() - 2 : splitIndex;
                split = DenominationUnit.list().get(splitIndex);

                if (whatRemains >= split.getValue()) {
                    tailView.incrementDenominationUnitCount(split, (short) 1);
                    whatRemains -= split.getValue();
                }

                splitIndex--;
            }

        }

        for (int j = gamePlayFragment.getGridView().getAdapter().getCount() - 1; j >= 0; j--) {
            GamePlayPersonTile tile = (GamePlayPersonTile) gamePlayFragment.getGridView().getAdapter().getItem(j);
            tile.decrementTimeRemaining((short) 5);
        }
    }

    @Override
    public int getMinimumAllowedHighScore() {
        return 1000;
    }

    @Override
    public void incrementHave(int offset) {
        super.incrementHave(offset);
        appData.incrementSplitLargestChangeCount(offset);
    }

    @Override
    public void decrementHave(int offset) {
        super.decrementHave(offset);
        appData.decrementSplitLargestChangeCount(offset);
    }
}
