package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.AllowPassengerEscapeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.ExactAmountMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.GiveChangeMission;

/**
 * Created by Daniel on 5/2/2015.
 */
public class MissionSeventeen extends Mission {

    public MissionSeventeen(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new ExactAmountMission(missionInfoHolder, level, this).setFactor(25));
        addSubMission(new AllowPassengerEscapeMission(missionInfoHolder, level, this).setFactor(5));
        addSubMission(new GiveChangeMission(missionInfoHolder, level, this).setFactor(2200));
    }

    @Override
    public int getMission() {
        return 17;
    }
}
