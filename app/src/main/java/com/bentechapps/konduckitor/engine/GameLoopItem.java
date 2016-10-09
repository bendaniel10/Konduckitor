package com.bentechapps.konduckitor.engine;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;

/**
 * Created by Daniel on 6/17/2015.
 */
public interface GameLoopItem {

    public void doGameUpdates(GamePlayFragment gamePlayFragment, double delta);

    public void doGameRender(GamePlayFragment gamePlayFragment);
}
