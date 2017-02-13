package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class SettleFemalesMission extends SubMission {


    public SettleFemalesMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        this.completedFactor = 14;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.female;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getNumberOfSettledFemales() >= getCompletedFactor() * level);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Settle %s female passengers, %s settled.", getCompletedFactor() * level, missionInfoHolder.getNumberOfSettledFemales());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }
}
