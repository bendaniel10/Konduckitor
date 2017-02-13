package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class GiveChangeMission extends SubMission {


    public GiveChangeMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        this.completedFactor = 500;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.give_change;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getTotalAmountPaidOut() >= getCompletedFactor() * level);

    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Give out change of %s, %s given", getCompletedFactor() * level, missionInfoHolder.getTotalAmountPaidOut());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }
}
