package com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.Hint;

/**
 * Created by BenTech on 3/8/2015.
 */
public class PlayButtonHint extends Hint {
    public PlayButtonHint(GamePlayTutorialFragment gamePlayTutorialFragment) {
        super(gamePlayTutorialFragment);
    }

    @Override
    public void doSubHint() {
        gamePlayTutorialFragment.displaySubHint("Press play", gamePlayTutorialFragment.getGamePlayHeaderView().getPauseButton());
        gamePlayTutorialFragment.setShowSubHint(false);
        gamePlayTutorialFragment.setShowHint(false);
    }

    @Override
    public void doHint() {
        super.doHint();
        gamePlayTutorialFragment.displayHint(R.string.tutorial_pause_hint, R.string.tutorial_pause_title, gamePlayTutorialFragment.getGamePlayHeaderView().getPauseButton());
        gamePlayTutorialFragment.setShowHint(false);
        gamePlayTutorialFragment.setShowSubHint(true);
    }

    @Override
    public boolean isPausedHint() {
        return true;
    }

    @Override
    public void moveToNextHint() {
        super.moveToNextHint();
        gamePlayTutorialFragment.setCurrentHint(new PersonTileHint(gamePlayTutorialFragment));
        gamePlayTutorialFragment.setShowHint(true);
    }
}
