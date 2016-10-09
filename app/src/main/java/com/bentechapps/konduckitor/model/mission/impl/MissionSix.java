package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.AllowPassengerEscapeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.DoNotUseDenominationMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.PlayTimeMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionSix extends Mission {
    public MissionSix(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new AllowPassengerEscapeMission(missionInfoHolder, level, this).setFactor(4));
        addSubMission(new DoNotUseDenominationMission(missionInfoHolder, level, this).setFactor(10));
        addSubMission(new PlayTimeMission(missionInfoHolder, level, this).setFactor(400));
    }

    @Override
    public int getMission() {
        return 6;
    }
}
