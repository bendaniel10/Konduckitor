package com.bentechapps.konduckitor.data;

import android.content.Context;
import android.media.AudioManager;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.BuildConfig;
import com.bentech.android.appcommons.preference.Preference;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.level.LevelFactory;

/**
 * Created by BenTech on 2/9/2015.
 */
public class ApplicationData extends Preference {

    private static transient ApplicationData instance = Preference.getPreference(ApplicationData.class);
    private final int systemRingerMode;
    private int currentLevel = 1;
    private int currentMission = 1;
    private boolean music = true;
    private boolean sfx = true;
    private int difficulty = 1;
    private int defaultPowerUp = 0;
    private int highScore;
    private int reputation = BuildConfig.DEBUG ? 200000 : 0;
    private int playTutorial;

    //power ups
    private int splitLargestChangeCount = 5;
    private int splitLargestChangeLevel;
    private int calmDownCount = 5;
    private int calmDownLevel;
    private int regenerateCount = 5;
    private int regenerateLevel;
    private int comedianCount = 5;
    private int comedianLevel;
    private int graGraCount = 5;
    private int graGraLevel;


    public ApplicationData() {
        this.systemRingerMode = ((AudioManager) AppCommons.getApplication().getSystemService(Context.AUDIO_SERVICE)).getRingerMode();
    }

    public static ApplicationData getInstance() {
        return instance;
    }

    public int getSplitLargestChangeLevel() {
        return splitLargestChangeLevel;
    }

    public void setSplitLargestChangeLevel(int splitLargestChangeLevel) {
        this.splitLargestChangeLevel = splitLargestChangeLevel;
    }

    public int getCalmDownLevel() {
        return calmDownLevel;
    }

    public void setCalmDownLevel(int calmDownLevel) {
        this.calmDownLevel = calmDownLevel;
    }

    public int getRegenerateLevel() {
        return regenerateLevel;
    }

    public void setRegenerateLevel(int regenerateLevel) {
        this.regenerateLevel = regenerateLevel;
    }

    public int getComedianLevel() {
        return comedianLevel;
    }

    public void setComedianLevel(int comedianLevel) {
        this.comedianLevel = comedianLevel;
    }

    public int getGraGraLevel() {
        return graGraLevel;
    }

    public void setGraGraLevel(int graGraLevel) {
        this.graGraLevel = graGraLevel;
    }

    public int getCurrentMission() {
        return currentMission;
    }

    public void setCurrentMission(int currentMission) {
        this.currentMission = currentMission;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public boolean isMusic() {
        return music && this.systemRingerMode == AudioManager.RINGER_MODE_NORMAL;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public void incrementMissionProgress(GamePlayFragment gamePlayFragment) {
        //save mission progress
        int currentMission = gamePlayFragment.getGamePlayFragmentData().getCurrentMission().getMission();//the mission that was curretnly played
        int currentLevel = gamePlayFragment.getGamePlayFragmentData().getCurrentLevel().getLevelNumber();//the level that was currently played
        int levelMissionsSize = Level.getCurrentLevel(context).listMission().size();
        int levelSize = LevelFactory.listLevels().size();
        if (currentMission == levelMissionsSize) {//is this the last mission for that level?
            if (currentLevel == getCurrentLevel() && levelSize > getCurrentLevel()) {//check to prevent incrementing past defined levels.
                setCurrentLevel(getCurrentLevel() + 1);
                setCurrentMission(1);
            }
        } else {
            if (getCurrentLevel() == currentLevel && levelMissionsSize > getCurrentMission() && currentMission == getCurrentMission()) {
                setCurrentMission(getCurrentMission() + 1);
            }
        }

        savePreference();
    }

    public void decrementMissionProgress() {
        if (getCurrentMission() != 1) {
            setCurrentMission(getCurrentMission() - 1);
        }

        if (getCurrentMission() == 1 && getCurrentLevel() > 1) {//for last mission for a level: go back to max mission, previous level;
            setCurrentMission(LevelFactory.listLevels().get(getCurrentLevel() - 2).listMission().size());
            setCurrentLevel(getCurrentLevel() - 1);
        }

        savePreference();
    }

    public boolean isSfx() {

        return sfx && this.systemRingerMode == AudioManager.RINGER_MODE_NORMAL;
    }

    public void setSfx(boolean sfx) {
        this.sfx = sfx;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDefaultPowerUp() {
        return defaultPowerUp;
    }

    public void setDefaultPowerUp(int defaultPowerUp) {
        this.defaultPowerUp = defaultPowerUp;
    }

    public int getCalmDownCount() {
        return calmDownCount;
    }

    public void setCalmDownCount(int calmDownCount) {
        this.calmDownCount = calmDownCount;
    }

    public int getRegenerateCount() {
        return regenerateCount;
    }

    public void setRegenerateCount(int regenerateCount) {
        this.regenerateCount = regenerateCount;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public int getComedianCount() {
        return comedianCount;
    }

    public void setComedianCount(int comedianCount) {
        this.comedianCount = comedianCount;
    }

    public int getGraGraCount() {
        return graGraCount;
    }

    public void setGraGraCount(int graGraCount) {
        this.graGraCount = graGraCount;
    }

    public void incrementGraGraCount(int count) {
        this.graGraCount += count;
    }

    public void decrementGraGraCount(int count) {
        this.graGraCount -= count;
    }

    public int getSplitLargestChangeCount() {
        return splitLargestChangeCount;
    }

    public void setSplitLargestChangeCount(int splitLargestChangeCount) {
        this.splitLargestChangeCount = splitLargestChangeCount;
    }

    public int getPlayTutorial() {
        return playTutorial;
    }

    public void setPlayTutorial(int playTutorial) {
        this.playTutorial = playTutorial;
    }

    public void incrementComedianCount(int count) {
        this.comedianCount += count;
    }

    public void decrementComedianCount(int count) {
        this.comedianCount -= count;
    }

    public void incrementCalmDownCount(int calmDownCount) {
        this.calmDownCount += calmDownCount;
    }

    public void decrementCalmDownCount(int calmDownCount) {
        this.calmDownCount -= calmDownCount;
    }

    public void incrementSplitLargestChangeCount(int offset) {
        this.splitLargestChangeCount += offset;

    }

    public void decrementSplitLargestChangeCount(int offset) {
        this.splitLargestChangeCount -= offset;
    }

    public void incrementRegenerateCount(int regenerateCount) {
        this.regenerateCount += regenerateCount;
    }

    public void decrementRegenerateCount(int regenerateCount) {
        this.regenerateCount -= regenerateCount;
    }

    public void incrementReputation(int reputation) {
        this.reputation += reputation;
    }

    public void decrementReputation(int reputation) {
        this.reputation -= reputation;
    }

    public void incrementSplitLargestChangeLevel(int offset) {
        this.splitLargestChangeLevel += offset;
    }

    public void incrementGraGraLevel(int offset) {
        this.graGraLevel += offset;
    }

    public void incrementRegenerateLevel(int offset) {
        this.regenerateLevel += offset;
    }

    public void incrementCalmDownLevel(int offset) {
        this.calmDownLevel += offset;
    }

    public void incrementComedianLevel(int offset) {
        this.comedianLevel += offset;
    }
}
