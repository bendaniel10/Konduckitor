package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.AllowPassengerEscapeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.DoNotUseDenominationMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.GiveChangeMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionNineteen extends Mission {
    public MissionNineteen(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new GiveChangeMission(missionInfoHolder, level, this).setFactor(2500));
        addSubMission(new DoNotUseDenominationMission(missionInfoHolder, level, this).setFactor(10));
        addSubMission(new AllowPassengerEscapeMission(missionInfoHolder, level, this).setFactor(6));
    }

    @Override
    public int getMission() {
        return 19;
    }
}
