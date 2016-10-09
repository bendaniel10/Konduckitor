package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.GiveChangeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainAngryPassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthAtThresholdMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionFourteen extends Mission {
    public MissionFourteen(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new MaintainHealthAtThresholdMission(missionInfoHolder, level, this).setFactor(35));
        addSubMission(new MaintainAngryPassengersMission(missionInfoHolder, level, this).setFactor(4));
        addSubMission(new GiveChangeMission(missionInfoHolder, level, this).setFactor(2000));
    }

    @Override
    public int getMission() {
        return 14;
    }
}
