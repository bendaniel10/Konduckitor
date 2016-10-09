package com.bentechapps.konduckitor.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.level.Level;

/**
 * Created by BenTech on 2/9/2015.
 */
public class ApplicationData {

    private static ApplicationData instance;
    private final SharedPreferences prefs;
    private final int systemRingerMode;
    private int graGraCount;
    private int currentLevel;
    private int currentMission;
    private int comedianCount;
    private boolean music;
    private boolean sfx;
    private int difficulty;
    private int defaultPowerUp;
    private int calmDownCount;
    private int regenerateCount;
    private int highScore;
    private int reputation;
    private Context context;
    private int splitLargestChangeCount;
    private int playTutorial;

    private enum PREF_NAME {
        MUSIC, SFX, SPLIT_LARGEST_CHANGE, DIFFICULTY, DEFAULT_POWER_UP,
        COMMEDIAN, CALM_DOWN_COUNT, REGENERATE_COUNT, GRA_GRA_COUNT, HIGHSCORE, PLAY_TUTORIAL,
        CURRENT_LEVEL, CURRENT_MISSION, REPUTATION
    }

    private ApplicationData(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(this.getClass().getSimpleName(), Context.MODE_PRIVATE);
        this.music = prefs.getBoolean(PREF_NAME.MUSIC.name(), true);
        this.sfx = prefs.getBoolean(PREF_NAME.SFX.name(), true);
        this.difficulty = prefs.getInt(PREF_NAME.DIFFICULTY.name(), 1);
        this.defaultPowerUp = prefs.getInt(PREF_NAME.DEFAULT_POWER_UP.name(), 0);
        this.calmDownCount = prefs.getInt(PREF_NAME.CALM_DOWN_COUNT.name(), 3);
        this.regenerateCount = prefs.getInt(PREF_NAME.REGENERATE_COUNT.name(), 3);
        this.splitLargestChangeCount = prefs.getInt(PREF_NAME.SPLIT_LARGEST_CHANGE.name(), 3);
        this.graGraCount = prefs.getInt(PREF_NAME.GRA_GRA_COUNT.name(), 3);
        this.comedianCount = prefs.getInt(PREF_NAME.COMMEDIAN.name(), 3);
        this.highScore = prefs.getInt(PREF_NAME.HIGHSCORE.name(), 0);
        this.reputation = prefs.getInt(PREF_NAME.REPUTATION.name(), 500);
        this.playTutorial = prefs.getInt(PREF_NAME.PLAY_TUTORIAL.name(), 0);
        this.systemRingerMode = ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).getRingerMode();
        this.currentLevel = prefs.getInt(PREF_NAME.CURRENT_LEVEL.name(), 1);
        this.currentMission = prefs.getInt(PREF_NAME.CURRENT_MISSION.name(), 1);
    }

    private SharedPreferences.Editor getEditor() {
        return prefs.edit();
    }

    public static ApplicationData getInstance(Context context) {
        if (instance == null) {
            synchronized (ApplicationData.class) {
                instance = new ApplicationData(context);
            }
        }
        return instance;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        getEditor().putInt(PREF_NAME.CURRENT_LEVEL.name(), currentLevel).apply();
    }

    public void setCurrentMission(int currentMission) {
        this.currentMission = currentMission;
        getEditor().putInt(PREF_NAME.CURRENT_MISSION.name(), currentMission).apply();
    }

    public int getCurrentMission() {
        return currentMission;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean isMusic() {
        return music && this.systemRingerMode == AudioManager.RINGER_MODE_NORMAL;
    }

    public void incrementMissionProgress(GamePlayFragment gamePlayFragment) {
        //save mission progress
        int currentMission = gamePlayFragment.getGamePlayFragmentData().getCurrentMission().getMission();//the mission that was curretnly played
        int currentLevel = gamePlayFragment.getGamePlayFragmentData().getCurrentLevel().getLevel();//the level that was currently played
        int levelMissionsSize = Level.getLevel(gamePlayFragment).listMission(new GamePlayFragment().setGamePlayFragmentData(new GamePlayFragmentData(context))).size();//find a better way to access this.
        int levelSize = Level.list().size();
        if (currentMission == levelMissionsSize) {//is this the last mission for that level?
            if (currentLevel == getCurrentLevel() && levelSize > getCurrentLevel()) {//check to prevent incrementing past defined levels.
                setCurrentLevel(getCurrentLevel() + 1);
                setCurrentMission(1);
            }
        } else {
            if(getCurrentLevel() == currentLevel && levelMissionsSize > getCurrentMission() && currentMission == getCurrentMission()) {
                setCurrentMission(getCurrentMission() + 1);
            }
        }
    }

    public void decrementMissionProgress() {
        if(getCurrentMission() != 1) {
            setCurrentMission(getCurrentMission() - 1);
        }

        if(getCurrentMission() == 1 && getCurrentLevel() > 1) {//for last mission for a level: go back to max mission, previous level;
            setCurrentMission(Level.list().get(getCurrentLevel() - 2).listMission(new GamePlayFragment().setGamePlayFragmentData(new GamePlayFragmentData(context))).size());//find a better way to access this.);
            setCurrentLevel(getCurrentLevel() - 1);
        }
    }

    public boolean isSfx() {

        return sfx && this.systemRingerMode == AudioManager.RINGER_MODE_NORMAL;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getDefaultPowerUp() {
        return defaultPowerUp;
    }

    public int getCalmDownCount() {
        return calmDownCount;
    }

    public int getRegenerateCount() {
        return regenerateCount;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getReputation() {
        return reputation;
    }

    public int getComedianCount() {
        return comedianCount;
    }

    public int getGraGraCount() {
        return graGraCount;
    }

    public void incrementGraGraCount(int count) {
        this.graGraCount += count;
        getEditor().putInt(PREF_NAME.GRA_GRA_COUNT.name(), this.graGraCount).apply();    }

    public void decrementGraGraCount(int count) {
        this.graGraCount -= count;
        getEditor().putInt(PREF_NAME.GRA_GRA_COUNT.name(), this.graGraCount).apply();
    }

    public void setMusic(boolean music) {
        this.music = music;
        getEditor().putBoolean(PREF_NAME.MUSIC.name(), music).apply();
    }

    public void setSfx(boolean sfx) {
        this.sfx = sfx;
        getEditor().putBoolean(PREF_NAME.SFX.name(), sfx).apply();
    }

    public int getSplitLargestChangeCount() {
        return splitLargestChangeCount;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        getEditor().putInt(PREF_NAME.DIFFICULTY.name(), difficulty).apply();
    }

    public int getPlayTutorial() {
        return playTutorial;
    }

    public void setPlayTutorial(int playTutorial) {
        this.playTutorial = playTutorial;
        getEditor().putInt(PREF_NAME.PLAY_TUTORIAL.name(), playTutorial).apply();
    }

    public void setDefaultPowerUp(int defaultPowerUp) {
        this.defaultPowerUp = defaultPowerUp;
        getEditor().putInt(PREF_NAME.DEFAULT_POWER_UP.name(), defaultPowerUp).apply();
    }

    public void incrementComedianCount(int count) {
        this.comedianCount += count;
        getEditor().putInt(PREF_NAME.COMMEDIAN.name(), this.comedianCount).apply();

    }

    public void decrementComedianCount(int count) {
        this.comedianCount -= count;
        getEditor().putInt(PREF_NAME.COMMEDIAN.name(), this.comedianCount).apply();
    }

    public void incrementCalmDownCount(int calmDownCount) {
        this.calmDownCount += calmDownCount;
        getEditor().putInt(PREF_NAME.CALM_DOWN_COUNT.name(), this.calmDownCount).apply();

    }

    public void decrementCalmDownCount(int calmDownCount) {
        this.calmDownCount -= calmDownCount;
        getEditor().putInt(PREF_NAME.CALM_DOWN_COUNT.name(), this.calmDownCount).apply();
    }

    public void incrementSplitLargestChangeCount(int offset) {
        this.splitLargestChangeCount += offset;
        getEditor().putInt(PREF_NAME.SPLIT_LARGEST_CHANGE.name(), this.splitLargestChangeCount).apply();

    }

    public void decrementSplitLargestChangeCount(int offset) {
        this.splitLargestChangeCount -= offset;
        getEditor().putInt(PREF_NAME.SPLIT_LARGEST_CHANGE.name(), this.splitLargestChangeCount).apply();
    }

    public void incrementRegenerateCount(int regenerateCount) {
        this.regenerateCount += regenerateCount;
        getEditor().putInt(PREF_NAME.REGENERATE_COUNT.name(), this.regenerateCount).apply();
    }

    public void decrementRegenerateCount(int regenerateCount) {
        this.regenerateCount -= regenerateCount;
        getEditor().putInt(PREF_NAME.REGENERATE_COUNT.name(), this.regenerateCount).apply();
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
        getEditor().putInt(PREF_NAME.HIGHSCORE.name(), highScore).apply();
    }

    public void incrementReputation(int reputation) {
        this.reputation += reputation;
        getEditor().putInt(PREF_NAME.REPUTATION.name(), this.reputation).apply();

    }

    public void decrementReputation(int reputation) {
        this.reputation -= reputation;
        getEditor().putInt(PREF_NAME.REPUTATION.name(), this.reputation).apply();

    }
}
