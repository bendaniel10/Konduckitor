package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettleFemalesMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainAngryPassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.PlayTimeMission;

/**
 * Created by Daniel on 4/11/2015.
 */
public class MissionThree extends Mission {

    private boolean subMissionThreeCompleted;

    public MissionThree(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new PlayTimeMission(missionInfoHolder, level, this).setFactor(300));
        addSubMission(new SettleFemalesMission(missionInfoHolder, level, this).setFactor(14));
        addSubMission(new MaintainAngryPassengersMission(missionInfoHolder, level, this).setFactor(2));
    }

    @Override
    public int getMission() {
        return 3;
    }
}
