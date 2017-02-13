package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class TimedPowerUpUseMission extends SubMission {

    public TimedPowerUpUseMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        completedFactor = 40;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.timed_power_up;
    }

    @Override
    public boolean isSubMissionCompleted() {

        //time frame for penalty has passed, return true.
        if (missionInfoHolder.getGamePlayTime() >= getCompletedFactor() * level) {
            return isComplete = true;
        }

        return missionInfoHolder.getGamePlayTime() < getCompletedFactor() * level && missionInfoHolder.getPowerUpUseCount() < 1;
//        boolean isTimeCompletedWithoutPowerUpUse = (missionInfoHolder.getGamePlayTime() >= getCompletedFactor() * level && missionInfoHolder.getPowerUpUseCount() < 1);
//        boolean isTimeNotReachedAndWithoutPowerUps = getCompletedFactor() * level > missionInfoHolder.getGamePlayTime() && missionInfoHolder.getPowerUpUseCount() < 1;
//        return isComplete || (isComplete = (missionInfoHolder.getGamePlayTime() >= getCompletedFactor() * level && missionInfoHolder.getPowerUpUseCount() < 1));
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Play for %s seconds before using any power ups, %s secs played; %s power ups used", getCompletedFactor() * level, missionInfoHolder.getGamePlayTime(), missionInfoHolder.getPowerUpUseCount());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return missionInfoHolder.getGamePlayTime() < getCompletedFactor() * level;
    }
}
