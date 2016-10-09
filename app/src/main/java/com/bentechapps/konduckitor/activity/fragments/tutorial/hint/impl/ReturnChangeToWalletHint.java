package com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.Hint;
import com.bentechapps.konduckitor.view.tutorial.GamePlayTutorialTailView;

/**
 * Created by BenTech on 3/8/2015.
 */
public class ReturnChangeToWalletHint extends Hint {
    public ReturnChangeToWalletHint(GamePlayTutorialFragment gamePlayTutorialFragment) {
        super(gamePlayTutorialFragment);
    }

    @Override
    public void doSubHint() {
        gamePlayTutorialFragment.displaySubHint("After selecting change, tap to give to passenger", gamePlayTutorialFragment.getGridView().getChildAt(0).findViewById(R.id.imageButton));
        gamePlayTutorialFragment.setShowSubHint(false);
        gamePlayTutorialFragment.setShowHint(false);
    }

    @Override
    public void doHint() {
        super.doHint();
        gamePlayTutorialFragment.displayHint("To clear wallet selection and return the money back to the wallet, tap the X button",
                "Clearing selection.", ((GamePlayTutorialTailView) gamePlayTutorialFragment.getGamePlayTailView()).getSelectButton());
        gamePlayTutorialFragment.setShowHint(false);
        gamePlayTutorialFragment.setShowSubHint(true);
    }

    @Override
    public void moveToNextHint() {
        super.moveToNextHint();
        gamePlayTutorialFragment.setShowHint(true);
        gamePlayTutorialFragment.setCurrentHint(new PowerUpHint(gamePlayTutorialFragment));
    }
}
