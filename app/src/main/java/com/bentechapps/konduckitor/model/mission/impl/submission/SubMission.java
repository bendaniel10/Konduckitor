package com.bentechapps.konduckitor.model.mission.impl.submission;

import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;

import java.io.Serializable;

/**
 * Created by Daniel on 4/12/2015.
 */
public abstract class SubMission implements Serializable{
    protected Mission parentMission;
    protected MissionInfoHolder missionInfoHolder;
    protected int level;
    protected boolean isComplete;
    public int completedFactor;
    private boolean isUserNotified;

    private SubMission() {
    }

    protected SubMission(MissionInfoHolder missionInfoHolder, int level) {
        this.missionInfoHolder = missionInfoHolder;
        this.level = level;
    }

    public int getCompletedFactor() {
        return completedFactor;
    }

    public SubMission setCompletedFactor(int completedFactor) {
        this.completedFactor = completedFactor;
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
        setIsUserNotified(false);
        isComplete = false;
        return this;
    }

    public void setParentMission(Mission parentMission) {
        this.parentMission = parentMission;
    }

    public Mission getParentMission() {
        return parentMission;
    }
}
