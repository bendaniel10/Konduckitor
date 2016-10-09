package com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.Hint;

/**
 * Created by BenTech on 3/8/2015.
 */
public class PersonTileHint extends Hint {
    public PersonTileHint(GamePlayTutorialFragment gamePlayTutorialFragment) {
        super(gamePlayTutorialFragment);
    }

    @Override
    public void doHint() {
        super.doHint();
        gamePlayTutorialFragment.displayHint(R.string.person_tile_hint, R.string.person_tile_title, gamePlayTutorialFragment.getGridView().getChildAt(0).findViewById(R.id.imageButton));
        gamePlayTutorialFragment.setShowHint(false);
        gamePlayTutorialFragment.setShowSubHint(true);
    }

    @Override
    public void doSubHint() {
        gamePlayTutorialFragment.displaySubHint("Tap to collect money from passenger", gamePlayTutorialFragment.getGridView().getChildAt(0).findViewById(R.id.imageButton));
        gamePlayTutorialFragment.setShowSubHint(false);
        gamePlayTutorialFragment.setShowHint(false);
    }

    @Override
    public void moveToNextHint() {
        super.moveToNextHint();
        gamePlayTutorialFragment.setCurrentHint(new PersonTileMoneyCollectedHint(gamePlayTutorialFragment));
        gamePlayTutorialFragment.setShowHint(true);
    }
}
