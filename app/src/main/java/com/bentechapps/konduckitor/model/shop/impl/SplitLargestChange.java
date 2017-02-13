package com.bentechapps.konduckitor.model.shop.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;
import com.bentechapps.konduckitor.model.person.Passenger;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.view.GamePlayTailView;
import com.bentechapps.konduckitor.view.custom.WalletButton;

/**
 * Created by Daniel on 2/10/2015.
 */
public class SplitLargestChange extends ShopItem {
    private final ApplicationData appData;
    private static final int MINIMUM_SPLIT = 5;
    public SplitLargestChange(Context context) {
        super(context);
        appData = ApplicationData.getInstance();
    }

    @Override
    public int getDuration() {
        return 1;
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
        return context.getString(R.string.split_largest_change_description, getUpgradeLevel() + MINIMUM_SPLIT);
    }

    @Override
    public int getCost() {
        return 1500;
    }

    @Override
    public void execute(final GamePlayFragment gamePlayFragment) {
        if (gamePlayFragment.getGamePlayFragmentData().getPowerUpDuration() == 1) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementSplitUseCount(1);
        }
        DenominationUnit largestDenomination = gamePlayFragment.getGamePlayTailView().getGamePlayTailData().getConductorWallet().getHundreds();
        GamePlayTailView tailView = gamePlayFragment.getGamePlayTailView();
        DenominationUnit split;
        int splitIndex;
        int largestCount = Math.min(largestDenomination.getCount(), getUpgradeLevel() + MINIMUM_SPLIT);//upgrade-level 0 will split 5 100s

        for (int i = 0; i < largestCount; i++) {//for each highest denomination
            tailView.decrementDenominationUnitCount((WalletButton) tailView.findViewById(R.id.hundreds), largestDenomination, (short) 1);
            splitIndex = DenominationUnit.list().size() - 2;
            for (int whatRemains = largestDenomination.getValue(); whatRemains > 0; ) {//while a largest denomination still has money
                splitIndex = splitIndex < 0 ? DenominationUnit.list().size() - 2 : splitIndex;
                split = DenominationUnit.list().get(splitIndex);

                if (whatRemains >= split.getValue()) {
                    tailView.incrementDenominationUnitCount(split, (short) 1);
                    whatRemains -= split.getValue();
                }

                splitIndex--;
            }

        }
        Passenger passenger;
        for (int j = gamePlayFragment.getPassengersAdapter().getItemCount() - 1; j >= 0; j--) {
            passenger = gamePlayFragment.getPassengersAdapter().getItem(j);

            if (passenger.getTimeLeft() < passenger.getExitTime()) {
                passenger.setTimeLeft((short) (passenger.getTimeLeft() - 5));
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
        return 1000;
    }

    @Override
    public void incrementHave(int offset) {
        super.incrementHave(offset);
        appData.incrementSplitLargestChangeCount(offset);
    }

    @Override
    public void incrementUpgradeLevel(int offset) {
        super.incrementUpgradeLevel(offset);
        appData.incrementSplitLargestChangeLevel(offset);
    }

    @Override
    public void decrementHave(int offset) {
        super.decrementHave(offset);
        appData.decrementSplitLargestChangeCount(offset);
    }


    @Override
    public int getUpgradeLevel() {
        return appData.getSplitLargestChangeLevel();
    }
}
