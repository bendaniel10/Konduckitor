package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/29/2015.
 */
public class TimedFinishOthersMission extends SubMission {
    public TimedFinishOthersMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        completedFactor = 400;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.timed_finish;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = isOnlyOneMissionLeftToComplete());
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Finish other missions in %s seconds, %s seconds remaining", getCompletedFactor() + (100 * level) , getCompletedFactor() + (100 * level) - missionInfoHolder.getGamePlayTime());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return missionInfoHolder.getGamePlayTime() >= getCompletedFactor()  + (100 * level);
    }

    private boolean isOnlyOneMissionLeftToComplete() {
        int size = parentMission.listMissions().size(), complete = 0;
        SubMission subMission;
        for(int i = 0; i < size; i++) {
            subMission = parentMission.listMissions().get(i);
            if(!(subMission instanceof TimedFinishOthersMission) && subMission.isSubMissionCompleted()) {
                complete++;
            }
        }
        return (complete == size - 1);
    }
}
