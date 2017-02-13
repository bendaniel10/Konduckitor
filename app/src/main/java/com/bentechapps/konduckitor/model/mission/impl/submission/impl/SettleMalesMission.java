package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class SettleMalesMission extends SubMission {


    public SettleMalesMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        completedFactor = 14;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.male;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getNumberOfSettledMales() >= getCompletedFactor() * level);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Settle %s male passengers, %s settled", getCompletedFactor() * level, missionInfoHolder.getNumberOfSettledMales());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }
}
