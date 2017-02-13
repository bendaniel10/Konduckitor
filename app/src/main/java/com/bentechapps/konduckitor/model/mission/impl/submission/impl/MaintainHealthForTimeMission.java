package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import android.util.Log;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class MaintainHealthForTimeMission extends SubMission {

    public MaintainHealthForTimeMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        completedFactor = 20;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.timed_health;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = (missionInfoHolder.getHealth() >= getCompletedFactor() + (level * 10) && missionInfoHolder.getGamePlayTime() >= 300 * level));
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Maintain a health of at least %s for %s secs, Health = %s; time remaining : %s",
                getCompletedFactor() + (level * 10), 300 * level, missionInfoHolder.getHealth(), neverNegative((300 * level) - missionInfoHolder.getGamePlayTime()));
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return (missionInfoHolder.getHealth() < getCompletedFactor() + (level * 10));
    }

    private long neverNegative(long i) {
        return i < 0 ? 0 : i;
    }
}
