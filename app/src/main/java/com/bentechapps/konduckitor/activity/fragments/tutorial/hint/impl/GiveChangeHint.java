package com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.Hint;
import com.bentechapps.konduckitor.view.tutorial.GamePlayTutorialTailView;
import com.github.amlcurran.showcaseview.ShowcaseView;

/**
 * Created by BenTech on 3/8/2015.
 */
public class GiveChangeHint extends Hint {
    public GiveChangeHint(GamePlayTutorialFragment gamePlayTutorialFragment) {
        super(gamePlayTutorialFragment);
    }

    @Override
    public void doSubHint() {
        gamePlayTutorialFragment.displaySubHint("Tap money in wallet to select change", ((GamePlayTutorialTailView) gamePlayTutorialFragment.getGamePlayTailView()).getFiftiesButton());
        gamePlayTutorialFragment.setShowSubHint(false);

    }

    @Override
    public void doHint() {
        super.doHint();

        gamePlayTutorialFragment.getView().post(new Runnable() {
            @Override
            public void run() {
                ShowcaseView showcaseView = new ShowcaseView.Builder(gamePlayTutorialFragment.getActivity(), true)
                        .setStyle(R.style.MyShowCaseTheme).build();
                showcaseView.setOnShowcaseEventListener(gamePlayTutorialFragment);
                gamePlayTutorialFragment.setShowcaseView(showcaseView);
                gamePlayTutorialFragment.displayHint(R.string.give_change_hint,
                        R.string.give_change_title, ((GamePlayTutorialTailView) gamePlayTutorialFragment.getGamePlayTailView()).getFiftiesButton());
                gamePlayTutorialFragment.setShowHint(false);
                gamePlayTutorialFragment.setShowSubHint(true);
            }
        });

    }

    @Override
    public void moveToNextHint() {
        super.moveToNextHint();
        gamePlayTutorialFragment.setCurrentHint(new ReturnChangeToWalletHint(gamePlayTutorialFragment));
        gamePlayTutorialFragment.setShowHint(true);
    }
}
