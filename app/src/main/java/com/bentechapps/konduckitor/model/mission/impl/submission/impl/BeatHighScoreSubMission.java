package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/29/2015.
 */
public class BeatHighScoreSubMission extends SubMission {

    public BeatHighScoreSubMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.trophy;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = missionInfoHolder.getScore() > missionInfoHolder.getHighScore());
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Beat your high score, %s remaining", Math.abs(missionInfoHolder.getHighScore() - missionInfoHolder.getScore()));
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return false;
    }
}
