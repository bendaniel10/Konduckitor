package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthAtThresholdMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.PlayTimeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettleMalesMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionEighteen extends Mission {
    public MissionEighteen(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new SettleMalesMission(missionInfoHolder, level, this).setFactor(18));
        addSubMission(new PlayTimeMission(missionInfoHolder, level, this).setFactor(500));
        addSubMission(new MaintainHealthAtThresholdMission(missionInfoHolder, level, this).setFactor(40));
    }

    @Override
    public int getMission() {
        return 18;
    }
}
