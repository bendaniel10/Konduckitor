package com.bentechapps.konduckitor.model.mission.impl.submission;

import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;

/**
 * Created by Daniel on 4/12/2015.
 */
public abstract class SubMission {
    protected Mission parentMission;
    protected MissionInfoHolder missionInfoHolder;
    protected int level;
    protected boolean isComplete;
    public int factor;
    private boolean isUserNotified;

    private SubMission() {
    }

    protected SubMission(MissionInfoHolder missionInfoHolder, int level, Mission parentMission) {
        this.missionInfoHolder = missionInfoHolder;
        this.level = level;
        this.parentMission = parentMission;
    }

    public int getFactor() {
        return factor;
    }

    public SubMission setFactor(int factor) {
        this.factor = factor;
        return this;
    }

    public boolean isUserNotified() {
        return isUserNotified;
    }

    public void setIsUserNotified(boolean isUserNotified) {
        this.isUserNotified = isUserNotified;
    }

    public abstract int getSubMissionDrawable();

    public abstract boolean isSubMissionCompleted();

    public abstract String getSubMissionDescription();

    public abstract boolean isGameOverOnSubMissionFail();

    public SubMission restartSubMission() {
        missionInfoHolder = parentMission.getGamePlayFragment().getGamePlayFragmentData().getMissionInfoHolder();
        setIsUserNotified(false);
        isComplete = false;
        return this;
    }
}
