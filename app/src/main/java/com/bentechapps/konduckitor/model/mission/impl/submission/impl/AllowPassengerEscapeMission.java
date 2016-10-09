package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class AllowPassengerEscapeMission extends SubMission {

    public AllowPassengerEscapeMission(MissionInfoHolder missionInfoHolder, int level, Mission parentMission) {
        super(missionInfoHolder, level, parentMission);
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.escape;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getEscapedPassengers() >= getFactor() * level);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Allow %s passengers escape without paying, %s allowed", getFactor() * level, missionInfoHolder.getEscapedPassengers());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }

}
