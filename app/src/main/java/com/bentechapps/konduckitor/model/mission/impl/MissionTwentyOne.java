package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainAngryPassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthForTimeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettleFemalesMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionTwentyOne extends Mission {
    public MissionTwentyOne(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new SettleFemalesMission(missionInfoHolder, level, this).setFactor(18));
        addSubMission(new MaintainHealthForTimeMission(missionInfoHolder, level, this).setFactor(45));
        addSubMission(new MaintainAngryPassengersMission(missionInfoHolder, level, this).setFactor(4));
    }

    @Override
    public int getMission() {
        return 21;
    }
}
