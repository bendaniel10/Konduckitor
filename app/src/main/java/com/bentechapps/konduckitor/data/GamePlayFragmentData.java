package com.bentechapps.konduckitor.data;

import android.content.Context;

import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;

/**
 * Created by BenTech on 2/8/2015.
 */
public class GamePlayFragmentData {
    private MissionInfoHolder missionInfoHolder;
    private boolean isPowerUpMode;
    private int powerUpDuration;
    private boolean isMissionMode;
    private Context context;
    private Mission currentMission;
    private Level currentLevel;
    private int unsettledPassengerHealthDeduction;
    private int settledPassengerRewardPoint;

    private GamePlayFragmentData() {
    }

    public GamePlayFragmentData(Context context) {
        this.context = context;
        setUnsettledPassengerHealthDeduction(1);
        setSettledPassengerRewardPoint(1);
        setMissionInfoHolder(new MissionInfoHolder());
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

    public void setPowerUpDuration(int powerUpDuration) {
        this.powerUpDuration = powerUpDuration;
    }

    public MissionInfoHolder getMissionInfoHolder() {
        return missionInfoHolder;
    }

    public void setMissionInfoHolder(MissionInfoHolder missionInfoHolder) {
        ApplicationData applicationData = ApplicationData.getInstance(context);
        missionInfoHolder.setHighScore(applicationData.getHighScore());
        this.missionInfoHolder = missionInfoHolder;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setUnsettledPassengerHealthDeduction(int unsettledPassengerHealthDeduction) {
        this.unsettledPassengerHealthDeduction = unsettledPassengerHealthDeduction;
    }

    public int getUnsettledPassengerHealthDeduction() {
        return unsettledPassengerHealthDeduction;
    }


    public void setSettledPassengerRewardPoint(int settledPassengerRewardPoint) {
        this.settledPassengerRewardPoint = settledPassengerRewardPoint;
    }

    public int getSettledPassengerRewardPoint() {
        return settledPassengerRewardPoint;
    }
}
