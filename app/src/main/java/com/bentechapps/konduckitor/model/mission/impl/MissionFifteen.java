package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.BeatHighScoreSubMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.DoNotUseDenominationMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.ExactAmountMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.GiveChangeMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionFifteen extends Mission {
    public MissionFifteen(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new ExactAmountMission(missionInfoHolder, level, this).setFactor(20));
        addSubMission(new DoNotUseDenominationMission(missionInfoHolder, level, this).setFactor(50));
        addSubMission(new BeatHighScoreSubMission(missionInfoHolder, level, this));
    }

    @Override
    public int getMission() {
        return 15;
    }
}
