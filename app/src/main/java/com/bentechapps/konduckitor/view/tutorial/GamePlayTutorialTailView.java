package com.bentechapps.konduckitor.view.tutorial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl.GiveChangeHint;
import com.bentechapps.konduckitor.view.GamePlayTailView;
import com.bentechapps.konduckitor.view.custom.WalletButton;

/**
 * Created by Daniel on 2/18/2015.
 */
public class GamePlayTutorialTailView extends GamePlayTailView {
    public GamePlayTutorialTailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageView getPowerUp() {
        return powerUp;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fives:
            case R.id.tens:
            case R.id.twenties:
            case R.id.fifties:
                if (!gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().isPaused()) {
                    if (((GamePlayTutorialFragment) gamePlayFragment).getCurrentHint() instanceof GiveChangeHint) {
                        ((GamePlayTutorialFragment) gamePlayFragment).getCurrentHint().moveToNextHint();
                    }
                }
            case R.id.hundreds:
                break;
            case R.id.powerUp:
                break;
        }
    }

    public WalletButton getFiftiesButton() {
        return this.fiftiesButton;
    }
}
