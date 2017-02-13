package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class SettlePassengersMission extends SubMission {

    public SettlePassengersMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        this.completedFactor = 20;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.passenger;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getNumberOfSettledPassengers() >= getCompletedFactor() * level);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Settle %s passengers, %s settled", getCompletedFactor() * level, missionInfoHolder.getNumberOfSettledPassengers());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }

}
