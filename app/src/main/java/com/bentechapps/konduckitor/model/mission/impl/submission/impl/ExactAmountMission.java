package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class ExactAmountMission extends SubMission {

    public ExactAmountMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        completedFactor = 10;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.money;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getExactFarePassengers() >= getCompletedFactor() * level);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Collect fare from %s passengers with exact amount, %s collected", getCompletedFactor() * level, missionInfoHolder.getExactFarePassengers());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }


}
