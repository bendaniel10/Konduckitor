package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.ExactAmountMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TimedFinishOthersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.UsePowerUpsMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionSeven extends Mission {
    public MissionSeven(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new UsePowerUpsMission(missionInfoHolder, level, this).setFactor(6));
        addSubMission(new ExactAmountMission(missionInfoHolder, level, this).setFactor(15));
        addSubMission(new TimedFinishOthersMission(missionInfoHolder, level, this).setFactor(400));
    }

    @Override
    public int getMission() {
        return 7;
    }
}
