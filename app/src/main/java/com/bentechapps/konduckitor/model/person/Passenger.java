package com.bentechapps.konduckitor.model.person;

import android.widget.TextView;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.data.GamePlayHeaderData;
import com.bentechapps.konduckitor.engine.GameLoopItem;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;
import com.bentechapps.konduckitor.sound.Sound;

/**
 * Created by BenTech on 1/31/2015.
 */

public class Passenger implements GameLoopItem {
    private int defaultImage;
    private int angryImage;
    private int impatientImage;
    private int settledImage;
    private boolean male;
    private DenominationUnit amountToPay;
    private DenominationUnit amountWith;
    private int exitTime;
    private int timeLeft;
    private PassengerState passengerState = PassengerState.DEFAULT;
    private double updateLength;
    private boolean isAngerRecorded;
    private boolean recycle;
    private boolean lowPatienceSignalled;
    private byte lowPatienceSignalledCount;

    public byte getLowPatienceSignalledCount() {
        return lowPatienceSignalledCount;
    }

    public void setLowPatienceSignalledCount(byte lowPatienceSignalledCount) {
        this.lowPatienceSignalledCount = lowPatienceSignalledCount;
    }

    public PassengerState getPassengerState() {
        return passengerState;
    }

    public void setPassengerState(PassengerState passengerState) {
        this.passengerState = passengerState;
    }

    @Override
    public boolean equals(Object obj) {
        if (defaultImage == 0) {
            return super.equals(obj);
        }

        return defaultImage == ((Passenger) obj).defaultImage;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + this.defaultImage;
    }

    public int getImageBasedOnState() {
        switch (passengerState) {
            case ANGRY:
                return angryImage;
            case DEFAULT:
                return defaultImage;
            case IMPATIENT:
                return impatientImage;
            case SETTLED:
                return settledImage;
        }
        return defaultImage;
    }

    public int getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(int defaultImage) {
        this.defaultImage = defaultImage;
    }

    public int getAngryImage() {
        return angryImage;
    }

    public void setAngryImage(int angryImage) {
        this.angryImage = angryImage;
    }

    public int getImpatientImage() {
        return impatientImage;
    }

    public void setImpatientImage(int impatientImage) {
        this.impatientImage = impatientImage;
    }

    public int getSettledImage() {
        return settledImage;
    }

    public void setSettledImage(int settledImage) {
        this.settledImage = settledImage;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
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

    public int getExitTime() {
        return exitTime;
    }

    public void setExitTime(int exitTime) {
        this.exitTime = exitTime;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = Math.max(timeLeft, 0);
        if (getTimeLeft() >= ((1D / 3D) * getExitTime())) {
            if(lowPatienceSignalled) {
                lowPatienceSignalled = false;
            }
        }
    }

    @Override
    public void doGameUpdates(GamePlayFragment gamePlayFragment, double delta) {

        updateLength += delta * GamePlayFragment.OPTIMAL_TIME;

        GamePlayHeaderData gamePlayHeaderData = gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData();
        setTimeLeft((short) (getTimeLeft() - Constants.PASSENGER_TIME_TICK));

        if (updateLength >= 1000000000) {//for each second
            if (getAmountToPay().isEmpty() && getAmountWith().getValue() >= 0 && getTimeLeft() > 0) {

                //Passenger has been settled, increment points
                if ((getTimeLeft() / GamePlayFragment.TARGET_FPS) % Constants.POINT_INCREASE_FREQUENCY_SECS == 0) {
                    gamePlayFragment.getGamePlayHeaderView().incrementPoints(gamePlayFragment.getGamePlayFragmentData().getSettledPassengerRewardPoint());
                }
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().setScore(gamePlayHeaderData.getPoints());
                setPassengerState(PassengerState.SETTLED);
            } else if (getAmountWith().getValue() < 0 && getTimeLeft() <= 0) {
                //passenger not yet settled and patience exhaused, decrement life
                gamePlayFragment.getGamePlayHeaderView().decrementLife(gamePlayFragment.getGamePlayFragmentData().getUnsettledPassengerHealthDeduction());
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().setHealth(gamePlayHeaderData.getLife());
            } else if (getAmountWith().getValue() < 0) {

                //passenger not yet settled but patience has NOT been exhausted.
                if (getTimeLeft() < ((2D / 3D) * getExitTime())) {
                    setPassengerState(PassengerState.IMPATIENT);
                }

                if (getTimeLeft() < ((1D / 3D) * getExitTime())) {
                    if(!lowPatienceSignalled) {
                        lowPatienceSignalled = true;
                    }
                    setPassengerState(PassengerState.ANGRY);
                }
            }
            updateLength = 0;
        }

        if (getTimeLeft() <= 0) {
            if (getAmountToPay().getValue() > 0) {
                //passenger left without paying
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementEscapedPassengers(1);
                gamePlayFragment.getGamePlayHeaderView().decrementLife(Constants.ESCAPED_PASSENGER_PENALTY);
            }
        }

    }

    @Override
    public void doGameRender(final GamePlayFragment gamePlayFragment) {

        final TextView pointsTextView = gamePlayFragment.getGamePlayHeaderView().getPointsTextView();
        final TextView healthTextView = gamePlayFragment.getGamePlayHeaderView().getHealthTextView();

        final GamePlayHeaderData gamePlayHeaderData = gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData();

        if (getAmountToPay().isEmpty() && getAmountWith().getValue() >= 0 && getTimeLeft() > 0) {
            //Passenger has been settled, increment points
            pointsTextView.post(new Runnable() {
                @Override
                public void run() {
                    pointsTextView.setText(String.valueOf(gamePlayHeaderData.getPoints()));
                }
            });
            recordAngerForMission(gamePlayFragment);
        } else if (getAmountWith().getValue() < 0 && getTimeLeft() <= 0) {
            //passenger not yet settled, play patience exhausted sound
            if (getTimeLeft() == 0) {
                Sound.playPassengerPatienceExhaustedSfx();
            }
            healthTextView.post(new Runnable() {
                @Override
                public void run() {
                    healthTextView.setText(String.valueOf(gamePlayHeaderData.getLife()));
                }
            });
            if (!isAngerRecorded) {
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementNumberOfAngryPassengers(1);
                isAngerRecorded = true;
            }
        } else if (getTimeLeft() <= 0) {
            //Passenger is good to to, exiting.
            setRecycle(true);
            healthTextView.post(new Runnable() {
                @Override
                public void run() {
                    Sound.playPassengerExitSfx();
                    recordAngerForMission(gamePlayFragment);//decrements angry passenger if was recorded as angry before.
                }
            });
        }
    }


    private void recordAngerForMission(GamePlayFragment gamePlayFragment) {
        if (isAngerRecorded) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().decrementNumberOfAngryPassengers(1);
            isAngerRecorded = false;
        }
    }

    public boolean isRecycle() {
        return recycle;
    }

    public void setRecycle(boolean recycle) {
        this.recycle = recycle;
    }

    public void recycle() {
        setRecycle(false);
        isAngerRecorded = false;
        passengerState = PassengerState.DEFAULT;
        timeLeft = exitTime;
        amountToPay = DenominationUnit.getRandomDenominationUnit();
        amountWith = DenominationUnit.getRandomDenominationUnit(amountToPay);
        lowPatienceSignalled = false;
        lowPatienceSignalledCount = 0;
    }

    public boolean isAngerRecorded() {
        return isAngerRecorded;
    }

    public void setAngerRecorded(boolean angerRecorded) {
        isAngerRecorded = angerRecorded;
    }

    public boolean isLowPatienceSignalled() {
        return lowPatienceSignalled;
    }

    public void setLowPatienceSignalled(boolean lowPatienceSignalled) {
        this.lowPatienceSignalled = lowPatienceSignalled;
    }
}
