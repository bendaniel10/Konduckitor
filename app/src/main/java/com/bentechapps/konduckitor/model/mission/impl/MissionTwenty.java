package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettlePassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TargetScoreMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TimedPowerUpUseMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionTwenty extends Mission {
    public MissionTwenty(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new TimedPowerUpUseMission(missionInfoHolder, level, this).setFactor(180));
        addSubMission(new TargetScoreMission(missionInfoHolder, level, this).setFactor(3500));
        addSubMission(new SettlePassengersMission(missionInfoHolder, level, this).setFactor(40));
    }

    @Override
    public int getMission() {
        return 20;
    }
}
