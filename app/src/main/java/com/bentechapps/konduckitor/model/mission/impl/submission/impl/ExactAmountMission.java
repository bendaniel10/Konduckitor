package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class ExactAmountMission extends SubMission {

    public ExactAmountMission(MissionInfoHolder missionInfoHolder, int level, Mission parentMission) {
        super(missionInfoHolder, level, parentMission);
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.money;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getExactFarePassengers() >= getFactor() * level);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Collect fare from %s passengers with exact amount, %s collected", getFactor() * level, missionInfoHolder.getExactFarePassengers());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }


}
