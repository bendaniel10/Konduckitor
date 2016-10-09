package com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.Hint;
import com.github.amlcurran.showcaseview.ShowcaseView;

/**
 * Created by BenTech on 3/8/2015.
 */
public class PersonTileMoneyCollectedHint extends Hint {

    public PersonTileMoneyCollectedHint(GamePlayTutorialFragment gamePlayTutorialFragment) {
        super(gamePlayTutorialFragment);
    }

    @Override
    public void doSubHint() {
        gamePlayTutorialFragment.setShowSubHint(false);
        moveToNextHint();
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
                gamePlayTutorialFragment.displayHint(R.string.person_tile_money_collected_hint,
                        R.string.person_tile_money_collected_title,
                        gamePlayTutorialFragment.getGridView().getChildAt(0).findViewById(R.id.imageButton));
                gamePlayTutorialFragment.setShowSubHint(true);
                gamePlayTutorialFragment.setShowHint(false);
            }
        });

    }

    @Override
    public void moveToNextHint() {
        super.moveToNextHint();
        gamePlayTutorialFragment.setCurrentHint(new GiveChangeHint(gamePlayTutorialFragment));
        gamePlayTutorialFragment.setShowHint(true);
        gamePlayTutorialFragment.setShowSubHint(false);
    }
}
