package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by bendaniel on 21/02/2017.
 */

public class OverPayPassengersSubMission extends SubMission {

    public OverPayPassengersSubMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.trophy;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getNumberOfOverpaidPassengers() >= getCompletedFactor() * level);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Overpay %s passengers, %s overpaid", getCompletedFactor() * level, missionInfoHolder.getNumberOfOverpaidPassengers());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }
}
