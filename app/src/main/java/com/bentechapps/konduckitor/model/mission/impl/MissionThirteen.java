package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthForTimeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettleMalesMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TimedFinishOthersMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionThirteen extends Mission    {
    public MissionThirteen(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new MaintainHealthForTimeMission(missionInfoHolder, level, this).setFactor(40));
        addSubMission(new SettleMalesMission(missionInfoHolder, level, this).setFactor(16));
        addSubMission(new TimedFinishOthersMission(missionInfoHolder, level, this).setFactor(500));
    }

    @Override
    public int getMission() {
        return 13;
    }
}
