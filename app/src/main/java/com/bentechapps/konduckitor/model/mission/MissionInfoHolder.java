package com.bentechapps.konduckitor.model.mission;

import com.bentechapps.konduckitor.data.GamePlayHeaderData;

import java.io.Serializable;

/**
 * Created by Daniel on 4/10/2015.
 */
public class MissionInfoHolder implements Serializable {

    private long gamePlayTime;
    private int health;
    private int score;
    private int numberOfSettledMales;
    private int numberOfSettledFemales;
    private int exactFarePassengers;
    private int totalAmountCollected;
    private int totalAmountPaidOut;
    private int powerUpUseCount;

    private int escapedPassengers;

    private int calmDownUseCount;
    private int splitUseCount;
    private int regenerateUseCount;
    private int comedianUseCount;
    private int graGraUseCount;
    private int numberOfAngryPassengers;
    private int highScore;
    private int tensUseCount;
    private int twentiesUseCount;
    private int fiftiesUseCount;
    private int hundredsUseCount;
    private int fivesUseCount;

    public MissionInfoHolder() {
        setHealth(GamePlayHeaderData.MAX_LIFE);
    }

    public int incrementTotalAmountCollected(int offset) {
        return this.totalAmountCollected += offset;
    }

    public int incrementTotalAmountPaidOut(int offset) {
        return this.totalAmountPaidOut += offset;
    }

    public int incrementHealth(int offset) {
        return this.health += offset;
    }

    public int decrementHealth(int offset) {
        return this.health -= offset;
    }

    public int incrementScore(int offset) {
        return this.score += offset;
    }

    public int incrementComedianUseCount(int count) {
        return this.comedianUseCount += count;
    }

    public int incrementRegenerateUseCount(int count) {
        return this.regenerateUseCount += count;
    }

    public int incrementSplitUseCount(int count) {
        return this.splitUseCount += count;
    }

    public int incrementCalmDownUseCount(int count) {
        return this.calmDownUseCount += count;
    }

    public int incrementNumberOfSettledMales(int males) {
        return this.numberOfSettledMales += males;
    }

    public int incrementNumberOfSettledFemales(int females) {
        return this.numberOfSettledFemales += females;
    }

    public int incrementPowerUpUseCount(int powerUpUseCount) {
        return this.powerUpUseCount += powerUpUseCount;
    }

    public int getTensUseCount() {
        return tensUseCount;
    }

    public int incrementTensUseCount(int tensUseCount) {
        return this.tensUseCount += tensUseCount;
    }

    public int getTwentiesUseCount() {
        return twentiesUseCount;
    }

    public int incrementTwentiesUseCount(int twentiesUseCount) {
        return this.twentiesUseCount += twentiesUseCount;
    }

    public int getFiftiesUseCount() {
        return fiftiesUseCount;
    }

    public int incrementFiftiesUseCount(int fiftiesUseCount) {
        return this.fiftiesUseCount += fiftiesUseCount;
    }

    public int getHundredsUseCount() {
        return hundredsUseCount;
    }

    public int incrementHundredsUseCount(int hundredsUseCount) {
        return this.hundredsUseCount += hundredsUseCount;
    }

    public int getFivesUseCount() {
        return fivesUseCount;
    }

    public void incrementFivesUseCount(int fivesUseCount) {
        this.fivesUseCount += fivesUseCount;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getTotalAmountPaidOut() {
        return totalAmountPaidOut;
    }

    public int getEscapedPassengers() {
        return escapedPassengers;
    }

    public int getCalmDownUseCount() {
        return calmDownUseCount;
    }

    public void setCalmDownUseCount(int calmDownUseCount) {
        this.calmDownUseCount = calmDownUseCount;
    }

    public int getSplitUseCount() {
        return splitUseCount;
    }

    public void setSplitUseCount(int splitUseCount) {
        this.splitUseCount = splitUseCount;
    }

    public int getRegenerateUseCount() {
        return regenerateUseCount;
    }


    public int getComedianUseCount() {
        return comedianUseCount;
    }

    public int getGraGraUseCount() {
        return graGraUseCount;
    }

    public void setGraGraUseCount(int graGraUseCount) {
        this.graGraUseCount = graGraUseCount;
    }

    public int incrementGraGraUseCount(int count) {
        return this.graGraUseCount += count;
    }

    public int incrementEscapedPassengers(int escapedPassengers) {
        return this.escapedPassengers += escapedPassengers;
    }

    public int getExactFarePassengers() {
        return exactFarePassengers;
    }

    public int incrementExactFarePassengers(int exactFarePassengers) {
        return this.exactFarePassengers += exactFarePassengers;
    }

    public long getGamePlayTime() {
        return gamePlayTime;
    }

    public void setGamePlayTime(long gamePlayTime) {
        this.gamePlayTime = gamePlayTime;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getNumberOfSettledMales() {
        return numberOfSettledMales;
    }

    public int getNumberOfSettledFemales() {
        return numberOfSettledFemales;
    }

    public int getNumberOfAngryPassengers() {
        return numberOfAngryPassengers;
    }

    public int incrementNumberOfAngryPassengers(int numberOfAngryPassengers) {
        return this.numberOfAngryPassengers += numberOfAngryPassengers;
    }

    public int decrementNumberOfAngryPassengers(int numberOfAngryPassengers) {
        if (numberOfAngryPassengers > 0) {
            return this.numberOfAngryPassengers -= numberOfAngryPassengers;
        }
        return numberOfAngryPassengers;
    }

    public int getNumberOfSettledPassengers() {
        return getNumberOfSettledMales() + getNumberOfSettledFemales();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalAmountCollected() {
        return totalAmountCollected;
    }

    public void setTotalAmountCollected(int totalAmountCollected) {
        this.totalAmountCollected = totalAmountCollected;
    }

    public int getPowerUpUseCount() {
        return getComedianUseCount() + getCalmDownUseCount() + getRegenerateUseCount() + getSplitUseCount() + getGraGraUseCount();
    }

    /**
     * Doesn't reset score. To enable stacking scores that will assist in creating highscores when multiple missions are
     * completed in succession.
     */
    public void reset() {
        gamePlayTime = 0;
        setHealth(GamePlayHeaderData.MAX_LIFE);

        //for continuity, this will help stack scores to beat high score in missions.
//        score = 0;
        numberOfSettledMales = 0;
        numberOfSettledFemales = 0;
        exactFarePassengers = 0;
        totalAmountCollected = 0;
        totalAmountPaidOut = 0;
        powerUpUseCount = 0;

        escapedPassengers = 0;

        calmDownUseCount = 0;
        splitUseCount = 0;
        regenerateUseCount = 0;
        comedianUseCount = 0;
        graGraUseCount = 0;
        numberOfAngryPassengers = 0;
        highScore = 0;
        tensUseCount = 0;
        twentiesUseCount = 0;
        fiftiesUseCount = 0;
        hundredsUseCount = 0;
        fivesUseCount = 0;
    }
}
