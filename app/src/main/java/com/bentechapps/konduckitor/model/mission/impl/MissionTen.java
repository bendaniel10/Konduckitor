package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.DoNotUseDenominationMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.GiveChangeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettleFemalesMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TimedPowerUpUseMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionTen extends Mission {
    public MissionTen(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new SettleFemalesMission(missionInfoHolder, level, this).setFactor(16));
        addSubMission(new GiveChangeMission(missionInfoHolder, level, this).setFactor(1400));
        addSubMission(new DoNotUseDenominationMission(missionInfoHolder, level, this).setFactor(20));
    }

    @Override
    public int getMission() {
        return 10;
    }
}
