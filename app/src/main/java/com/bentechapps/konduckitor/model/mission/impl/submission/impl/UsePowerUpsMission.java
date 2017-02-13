package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class UsePowerUpsMission extends SubMission {

    public UsePowerUpsMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        this.completedFactor = 4;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.power_up;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getPowerUpUseCount() >= getCompletedFactor() + level);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Use %s power ups, %s used", getCompletedFactor() + level, missionInfoHolder.getPowerUpUseCount());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }
}
