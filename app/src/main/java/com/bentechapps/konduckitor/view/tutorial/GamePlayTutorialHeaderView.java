package com.bentechapps.konduckitor.view.tutorial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl.PlayButtonHint;
import com.bentechapps.konduckitor.view.GamePlayHeaderView;

/**
 * Created by Daniel on 2/18/2015.
 */
public class GamePlayTutorialHeaderView extends GamePlayHeaderView {

    public GamePlayTutorialHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void handlePause() {
        super.handlePause();
        GamePlayTutorialFragment gamePlayTutorialFragment = ((GamePlayTutorialFragment) mainActivity.getGamePlayFragment());
        if (gamePlayTutorialFragment.getCurrentHint() instanceof PlayButtonHint) {
            gamePlayTutorialFragment.getCurrentHint().moveToNextHint();
        }

    }

    @Override
    public void decrementLife(int offset) {
        if (gamePlayHeaderData.getLife() <= 0) {
            appData.setPlayTutorial(0);
            super.decrementLife(offset);
            return;
        }
        gamePlayHeaderData.decrementLife(offset);
        updateLifeTextView();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.pauseButton:
                ((GamePlayTutorialFragment) mainActivity.getGamePlayFragment()).dismissHint();
        }
    }

    @Override
    public void incrementPlayTime(int offset) {
        super.incrementPlayTime(offset);
    }
}
