package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.GiveChangeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.UsePowerUpsMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthAtThresholdMission;

/**
 * Created by Daniel on 4/11/2015.
 */
public class MissionTwo extends Mission {

    public MissionTwo(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new UsePowerUpsMission(missionInfoHolder, level, this).setFactor(4));
        addSubMission(new GiveChangeMission(missionInfoHolder, level, this).setFactor(500));
        addSubMission(new MaintainHealthAtThresholdMission(missionInfoHolder, level, this).setFactor(20));
    }

    @Override
    public int getMission() {
        return 2;
    }
}
