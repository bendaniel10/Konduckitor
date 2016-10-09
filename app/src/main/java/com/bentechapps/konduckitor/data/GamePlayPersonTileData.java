package com.bentechapps.konduckitor.data;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;
import com.bentechapps.konduckitor.model.person.Person;

/**
 * Created by BenTech on 1/31/2015.
 */
public class GamePlayPersonTileData {
    private Person person;
    private DenominationUnit amountToPay;
    private DenominationUnit amountWith;
    private short timeLeft;
    private short exitTime;

    private GamePlayPersonTileData() {
    }

    public static GamePlayPersonTileData createPersonTileInfo(Person person, DenominationUnit amountToPay, DenominationUnit amountWith, short exitTime) {
        GamePlayPersonTileData personTile = new GamePlayPersonTileData();
        personTile.setPerson(person);
        personTile.setAmountToPay(amountToPay);
        personTile.setAmountWith(amountWith);
        personTile.setTimeLeft((short) (exitTime * GamePlayFragment.TARGET_FPS));
        personTile.setExitTime((short) (exitTime * GamePlayFragment.TARGET_FPS));
        return personTile;
    }

    public void incrementTimeLeft(short offset) {
        setTimeLeft((short) (getTimeLeft() + offset));
    }

    public void decrementTimeLeft(short offset) {
        setTimeLeft((short) (getTimeLeft() - offset));
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public DenominationUnit getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(DenominationUnit amountToPay) {
        this.amountToPay = amountToPay;
    }

    public DenominationUnit getAmountWith() {
        return amountWith;
    }

    public void setAmountWith(DenominationUnit amountWith) {
        this.amountWith = amountWith;
    }

    public short getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(short timeLeft) {
        this.timeLeft = timeLeft;
    }

    public short getExitTime() {
        return exitTime;
    }

    public void setExitTime(short exitTime) {
        this.exitTime = exitTime;
    }
}
