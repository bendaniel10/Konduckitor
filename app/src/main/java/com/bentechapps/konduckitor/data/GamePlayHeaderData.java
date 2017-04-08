package com.bentechapps.konduckitor.data;

import com.bentechapps.konduckitor.app.Constants;

/**
 * Created by BenTech on 2/7/2015.
 */
public class GamePlayHeaderData {
    public static int MAX_LIFE = 100;
    private boolean isPaused;
    private boolean isSaved;
    private long playTime;
    private int life;//max 100
    private int points;

    private GamePlayHeaderData(boolean isPaused, long playTime, int life, int points) {
        this.isPaused = isPaused;
        this.playTime = playTime;
        this.life = life;
        this.points = points;
    }

    public static GamePlayHeaderData newGamePlayHeaderData(boolean isPaused, long playTime, int life, int points) {
        return new GamePlayHeaderData(isPaused, playTime, life, points);
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void incrementPlayTime(long playTime) {
        this.playTime += playTime;
    }

    public int getLife() {
        return life;
    }

    public void incrementLife(int life) {
        this.life += life;
        this.life = Math.min(MAX_LIFE, this.life);
    }

    public void decrementLife(int life) {
        this.life -= life;
        if (this.life < 0)
            this.life = 0;
    }

    public int getPoints() {
        return points;
    }

    public void incrementPoints(int points) {
        this.points += points;
    }

    /**
     * Resets the data except for points. Points will not be resetAllExceptScore to enable stacking of scores for
     * the highscore feature when missions are completed in succession.
     */
    public void reset() {
        isPaused = true;
        isSaved = false;
        playTime = 0;
        life = MAX_LIFE;
//        points should not be resetAllExceptScore to enable score stacking through missions
    }
}
