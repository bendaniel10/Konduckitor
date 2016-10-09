package com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.Hint;
import com.bentechapps.konduckitor.view.tutorial.GamePlayTutorialTailView;

/**
 * Created by BenTech on 3/8/2015.
 */
public class PowerUpHint extends Hint {
    public PowerUpHint(GamePlayTutorialFragment gamePlayTutorialFragment) {
        super(gamePlayTutorialFragment);
    }

    @Override
    public void doSubHint() {

    }

    @Override
    public void doHint() {
        super.doHint();
        gamePlayTutorialFragment.displayHint(R.string.power_up_hint, R.string.power_up_title, ((GamePlayTutorialTailView) gamePlayTutorialFragment.getGamePlayTailView()).getPowerUp());
        gamePlayTutorialFragment.setShowHint(false);
        gamePlayTutorialFragment.setShowSubHint(false);
    }
}
