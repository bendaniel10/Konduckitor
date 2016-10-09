package com.bentechapps.konduckitor.activity.fragments.tutorial.hint;

import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl.NoHint;

/**
 * Created by BenTech on 3/8/2015.
 */
public abstract class Hint {

    protected GamePlayTutorialFragment gamePlayTutorialFragment;

    public Hint(GamePlayTutorialFragment gamePlayTutorialFragment) {
        this.gamePlayTutorialFragment = gamePlayTutorialFragment;
    }

    public void doHint() {
        gamePlayTutorialFragment.getGamePlayHeaderView().getGamePlayHeaderData().setPaused(!(this instanceof NoHint));
        gamePlayTutorialFragment.getGamePlayHeaderView().updatePauseView();
    }

    public abstract void doSubHint();

    public boolean isPausedHint() {
        return false;
    }

    public void moveToNextHint() {
        if(gamePlayTutorialFragment.getShowcaseView().isShown()) {
            gamePlayTutorialFragment.dismissHint();
        }
        if(gamePlayTutorialFragment.getSubHintView().isShown()) {
            gamePlayTutorialFragment.getSubHintView().remove();
        }
    }

}
