package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class MaintainAngryPassengersMission extends SubMission {

    public MaintainAngryPassengersMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        completedFactor = 2;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.angry;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = (missionInfoHolder.getNumberOfAngryPassengers() >= getCompletedFactor() + level));
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Owe %s passengers past their trip duration, %s angry", getCompletedFactor() + level, missionInfoHolder.getNumberOfAngryPassengers());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }
}
