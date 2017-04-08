package com.bentechapps.konduckitor.data;

import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;

import java.io.Serializable;

/**
 * Created by BenTech on 2/8/2015.
 */
public class GamePlayFragmentData implements Serializable {
    private MissionInfoHolder missionInfoHolder;
    private boolean isPowerUpMode;
    private int powerUpDuration;
    private boolean isMissionMode;
    private Mission currentMission;
    private Level currentLevel;
    private int unsettledPassengerHealthDeduction;
    private int settledPassengerRewardPoint;

    public GamePlayFragmentData() {
        setUnsettledPassengerHealthDeduction(1);
        setSettledPassengerRewardPoint(1);
    }

    public boolean isPowerUpMode() {
        return isPowerUpMode;
    }

    public void setPowerUpMode(boolean isPowerUpMode) {
        this.isPowerUpMode = isPowerUpMode;
    }

    public int getPowerUpDuration() {
        return powerUpDuration;
    }

    public void setPowerUpDuration(int powerUpDuration) {
        this.powerUpDuration = powerUpDuration;
    }

    public int incrementPowerUpDuration(int powerUpDuration) {
        return this.powerUpDuration += powerUpDuration;
    }

    public boolean isMissionMode() {
        return isMissionMode;
    }

    public Mission getCurrentMission() {
        return currentMission;
    }

    public void setCurrentMission(Mission currentMission) {
        this.currentMission = currentMission;
    }

    public void setIsMissionMode(boolean isMissionMode) {
        this.isMissionMode = isMissionMode;
    }

    public MissionInfoHolder getMissionInfoHolder() {
        return missionInfoHolder;
    }

    public void setMissionInfoHolder(MissionInfoHolder missionInfoHolder) {
        ApplicationData applicationData = ApplicationData.getInstance();
        missionInfoHolder.setHighScore(applicationData.getHighScore());
        this.missionInfoHolder = missionInfoHolder;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getUnsettledPassengerHealthDeduction() {
        return unsettledPassengerHealthDeduction;
    }

    public void setUnsettledPassengerHealthDeduction(int unsettledPassengerHealthDeduction) {
        this.unsettledPassengerHealthDeduction = unsettledPassengerHealthDeduction;
    }

    public int getSettledPassengerRewardPoint() {
        return settledPassengerRewardPoint;
    }

    public void setSettledPassengerRewardPoint(int settledPassengerRewardPoint) {
        this.settledPassengerRewardPoint = settledPassengerRewardPoint;
    }
}
