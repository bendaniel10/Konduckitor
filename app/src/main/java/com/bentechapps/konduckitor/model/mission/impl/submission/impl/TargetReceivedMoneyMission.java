package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class TargetReceivedMoneyMission extends SubMission {

    public TargetReceivedMoneyMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        this.completedFactor = 2000;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.receive;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return missionInfoHolder.getTotalAmountCollected() >= getCompletedFactor() * level;
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Collect %s, %s collected", getCompletedFactor() * level, missionInfoHolder.getTotalAmountCollected());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }

}
