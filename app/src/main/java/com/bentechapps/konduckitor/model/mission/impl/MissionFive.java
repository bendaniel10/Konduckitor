package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthForTimeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettleMalesMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettlePassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TimedPowerUpUseMission;

/**
 * Created by Daniel on 4/11/2015.
 */
public class MissionFive extends Mission {
    public MissionFive(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new TimedPowerUpUseMission(missionInfoHolder, level, this).setFactor(120));
        addSubMission(new SettleMalesMission(missionInfoHolder, level, this).setFactor(14));
        addSubMission(new SettlePassengersMission(missionInfoHolder, level, this).setFactor(25));
    }

    @Override
    public int getMission() {
        return 5;
    }
}
