package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.GiveChangeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthForTimeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TargetScoreMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionEight extends Mission{

    public MissionEight(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new MaintainHealthForTimeMission(missionInfoHolder, level, this).setFactor(30));
        addSubMission(new GiveChangeMission(missionInfoHolder, level, this).setFactor(1000));
        addSubMission(new TargetScoreMission(missionInfoHolder, level, this).setFactor(1000));
    }

    @Override
    public int getMission() {
        return 8;
    }
}
