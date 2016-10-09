package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class TargetReceivedMoneyMission extends SubMission {

    public TargetReceivedMoneyMission(MissionInfoHolder missionInfoHolder, int level, Mission parentMission) {
        super(missionInfoHolder, level, parentMission);
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.receive;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return missionInfoHolder.getTotalAmountCollected() >= getFactor() * level;
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Collect %s, %s collected", getFactor() * level, missionInfoHolder.getTotalAmountCollected());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }

}
