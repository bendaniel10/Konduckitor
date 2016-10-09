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

    public MaintainHealthForTimeMission(MissionInfoHolder missionInfoHolder, int level, Mission parentMission) {
        super(missionInfoHolder, level, parentMission);
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.timed_health;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return isComplete || (isComplete = (missionInfoHolder.getHealth() >= getFactor() + (level * 10) && missionInfoHolder.getGamePlayTime() >= 300 * level));
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Maintain a health of at least %s for %s secs, Health = %s; time remaining : %s",
                getFactor() + (level * 10), 300 * level, missionInfoHolder.getHealth(), neverNegative((300 * level) - missionInfoHolder.getGamePlayTime()));
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return (missionInfoHolder.getHealth() < getFactor() + (level * 10));
    }

    @Override
    public SubMission setFactor(int factor) {
        if(factor > 96) {
            Log.i("INFO", "Max health will be exceeeded, defaulting to 96");
            factor = 96;
        }
        return super.setFactor(factor);
    }

    private long neverNegative(long i) {
        return i < 0 ? 0 : i;
    }
}
