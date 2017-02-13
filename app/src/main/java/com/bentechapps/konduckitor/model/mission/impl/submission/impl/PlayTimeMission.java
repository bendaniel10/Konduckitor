package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/12/2015.
 */
public class PlayTimeMission extends SubMission {


    public PlayTimeMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
        completedFactor = 300;
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.timer;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return missionInfoHolder.getGamePlayTime() >= getCompletedFactor() * level;
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Play for at least %s seconds, %s played", getCompletedFactor() * level, missionInfoHolder.getGamePlayTime());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }
}
