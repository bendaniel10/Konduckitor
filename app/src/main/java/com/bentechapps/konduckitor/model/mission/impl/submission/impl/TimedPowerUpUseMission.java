package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class TimedPowerUpUseMission extends SubMission {

    public TimedPowerUpUseMission(MissionInfoHolder missionInfoHolder, int level, Mission parentMission) {
        super(missionInfoHolder, level, parentMission);
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.timed_power_up;
    }

    @Override
    public boolean isSubMissionCompleted() {
//        return missionInfoHolder.getPowerUpUseCount() < 1;
//        return (missionInfoHolder.getGamePlayTime() >= getFactor() * level && missionInfoHolder.getPowerUpUseCount() < 1);
        return isComplete || (isComplete = (missionInfoHolder.getGamePlayTime() >= getFactor() * level && missionInfoHolder.getPowerUpUseCount() < 1));
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Play for %s seconds before using any power ups, %s secs played; %s power ups used", getFactor() * level, missionInfoHolder.getGamePlayTime(), missionInfoHolder.getPowerUpUseCount());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return missionInfoHolder.getGamePlayTime() < getFactor() * level;
    }
}
