package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class MaintainHealthAtThresholdMission extends SubMission {


    public MaintainHealthAtThresholdMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        completedFactor = 20;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.health;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return missionInfoHolder.getHealth() >= getCompletedFactor() + (level * 10);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Don't let your health go below %s, %s left", getCompletedFactor() +  (level * 10), missionInfoHolder.getHealth());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return true;
    }

}
