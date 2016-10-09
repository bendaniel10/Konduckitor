package com.bentechapps.konduckitor.view.tutorial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl.PersonTileHint;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl.PersonTileMoneyCollectedHint;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl.ReturnChangeToWalletHint;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.GamePlayPersonTile;

/**
 * Created by Daniel on 2/18/2015.
 */
public class GamePlayTutorialPersonTile extends GamePlayPersonTile {


    public GamePlayTutorialPersonTile(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void decrementTimeRemaining(short offset) {
        super.decrementTimeRemaining(offset);
    }

    @Override
    protected void handleCredit() {
        if (!getGamePlayPersonTileData().getAmountToPay().isEmpty()) {
            Toast.makeText(getContext(), "Collect my money first.", Toast.LENGTH_SHORT).show();
        }
        super.handleCredit();

    }

    @Override
    protected void handleDebit() {
        super.handleDebit();
    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (!gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().isPaused()) {
            if (((GamePlayTutorialFragment) gamePlayFragment).getCurrentHint() instanceof PersonTileHint) {
                ((GamePlayTutorialFragment) gamePlayFragment).getCurrentHint().moveToNextHint();
            } else if (((GamePlayTutorialFragment) gamePlayFragment).getCurrentHint() instanceof PersonTileMoneyCollectedHint) {
                ((GamePlayTutorialFragment) gamePlayFragment).getCurrentHint().moveToNextHint();
            } else if (((GamePlayTutorialFragment) gamePlayFragment).getCurrentHint() instanceof ReturnChangeToWalletHint) {
                ((GamePlayTutorialFragment) gamePlayFragment).getCurrentHint().moveToNextHint();
            }
        }
    }
}
