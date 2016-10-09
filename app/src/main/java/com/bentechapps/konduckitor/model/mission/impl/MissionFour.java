package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.AllowPassengerEscapeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.ExactAmountMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthForTimeMission;

/**
 * Created by Daniel on 4/11/2015.
 */
public class MissionFour extends Mission {
    public MissionFour(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new AllowPassengerEscapeMission(missionInfoHolder, level, this).setFactor(3));
        addSubMission(new ExactAmountMission(missionInfoHolder, level, this).setFactor(10));
        addSubMission(new MaintainHealthForTimeMission(missionInfoHolder, level, this).setFactor(20));
    }

    @Override
    public int getMission() {
        return 4;
    }
}
