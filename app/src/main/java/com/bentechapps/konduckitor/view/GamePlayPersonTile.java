package com.bentechapps.konduckitor.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayHeaderData;
import com.bentechapps.konduckitor.data.GamePlayPersonTileData;
import com.bentechapps.konduckitor.engine.GameLoopItem;
import com.bentechapps.konduckitor.model.difficulty.Difficulty;
import com.bentechapps.konduckitor.model.person.Sex;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.animation.AnimationFactory;

/**
 * Created by BenTech on 1/31/2015.
 */
//Honest passenger, when you want to give more than the change. The passenger will inform you.
public class GamePlayPersonTile extends RelativeLayout implements GameLoopItem {

    protected final ImageView silohouette;
    protected final GamePlayFragment gamePlayFragment;
    protected final ApplicationData appData;
    private final ProgressBar progress;
    private final TextView amountWith;
    private final TextView amountToPay;
    private final LinearLayout amountHolder;
    public boolean isSettledRecorded;
    public boolean isAngerRecorded;
    private boolean isListenerSet = false;
    private GamePlayPersonTileData gamePlayPersonTileData;
    private short resetTime;
    private double updateLength;

    public GamePlayPersonTile(Context context, AttributeSet attrs) {
        super(context, attrs);
        gamePlayFragment = (GamePlayFragment) ((MainActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.user_tile, this, true);
        progress = (ProgressBar) findViewById(R.id.timeLeftBar);
        silohouette = (ImageView) findViewById(R.id.imageButton);
        amountWith = (TextView) findViewById(R.id.amountWith);
        amountToPay = (TextView) findViewById(R.id.amountToPay);
        this.appData = ApplicationData.getInstance(context);
        this.amountHolder = (LinearLayout) findViewById(R.id.amountHolder);

    }

    public GamePlayPersonTileData getGamePlayPersonTileData() {
        return gamePlayPersonTileData;
    }

    public void setGamePlayPersonTileData(GamePlayPersonTileData gamePlayPersonTileData) {
        this.gamePlayPersonTileData = gamePlayPersonTileData;
        progress.setMax(this.gamePlayPersonTileData.getExitTime());
        silohouette.setImageDrawable(this.gamePlayPersonTileData.getPerson().getImage());
        amountToPay.setText(Short.toString(this.gamePlayPersonTileData.getAmountToPay().getValue()));
        amountWith.setText(String.format("%s%s", "+", Short.toString(this.gamePlayPersonTileData.getAmountWith().getValue())));
        amountToPay.setTextColor(getResources().getColor(R.color.black));
        amountWith.setTextColor(getResources().getColor(R.color.black));

        isSettledRecorded = false;
        isAngerRecorded = false;
        amountWith.setTypeface(null, Typeface.BOLD);
        updateProgressBar();
        updateBackgroundImage();
    }

    public void setGamePlayPersonTileDataWitohoutInit(GamePlayPersonTileData gamePlayPersonTileData) {
        this.gamePlayPersonTileData = gamePlayPersonTileData;
    }


    public void incrementAmountWith(short offset) {
        this.getGamePlayPersonTileData().getAmountWith().incrementValue(offset);
        this.getGamePlayPersonTileData().getAmountWith().incrementCount((short) 1);

        amountWith.post(new Runnable() {
            @Override
            public void run() {
                amountWith.setText(Short.toString(GamePlayPersonTile.this.gamePlayPersonTileData.getAmountWith().getValue()));
                if (GamePlayPersonTile.this.gamePlayPersonTileData.getAmountWith().getValue() < 0) {
                    amountWith.setTextColor(Color.RED);
                } else {
                    amountWith.setTextColor(Color.BLACK);
                }
            }
        });
    }

    public void decrementAmountWith(short offset) {
        this.getGamePlayPersonTileData().getAmountWith().decrementValue(offset);
        this.getGamePlayPersonTileData().getAmountWith().decrementCount((short) 1);
        amountWith.post(new Runnable() {
            @Override
            public void run() {
                amountWith.setText(Short.toString(GamePlayPersonTile.this.gamePlayPersonTileData.getAmountWith().getValue()));
                if (GamePlayPersonTile.this.gamePlayPersonTileData.getAmountWith().getValue() < 0) {
                    amountWith.setTextColor(Color.RED);
                } else {
                    amountWith.setTextColor(Color.BLACK);
                }
            }
        });
    }

    public void incrementAmountToPay(short offset) {
        this.getGamePlayPersonTileData().getAmountToPay().incrementValue(offset);
        this.getGamePlayPersonTileData().getAmountToPay().incrementCount((short) 1);

        amountToPay.post(new Runnable() {
            @Override
            public void run() {
                amountToPay.setText(Short.toString(GamePlayPersonTile.this.gamePlayPersonTileData.getAmountToPay().getValue()));
            }
        });
    }

    public void decrementAmountToPay(short offset) {
        this.getGamePlayPersonTileData().getAmountToPay().decrementValue(offset);
        this.getGamePlayPersonTileData().getAmountToPay().decrementCount((short) 1);

        amountToPay.post(new Runnable() {
            @Override
            public void run() {
                amountToPay.setText(Short.toString(GamePlayPersonTile.this.gamePlayPersonTileData.getAmountToPay().getValue()));
            }
        });
    }


    public void incrementTimeRemaining(short offset) {
        if (gamePlayPersonTileData.getTimeLeft() < gamePlayPersonTileData.getExitTime()) {
            gamePlayPersonTileData.incrementTimeLeft(offset);
            updateProgressBar();
            updateBackgroundImage();
        }
    }

    public void decrementTimeRemaining(final short offset) {
        gamePlayPersonTileData.decrementTimeLeft(offset);
    }

    private void incrementPointsBasedOnDifficulty() {
        if (isCanIncrementPoint())
            gamePlayFragment.getGamePlayHeaderView().incrementPoints(gamePlayFragment.getGamePlayFragmentData().getSettledPassengerRewardPoint());
    }

    private boolean isCanIncrementPoint() {
        return (gamePlayPersonTileData.getTimeLeft() / GamePlayFragment.TARGET_FPS) % Difficulty.list().get(appData.getDifficulty()).getPointIncreaseDelayTime() == 0;
    }

    private void updateBackgroundImage() {
        this.post(new Runnable() {

            @Override
            public void run() {
                if (gamePlayPersonTileData.getAmountToPay().getValue() == 0 && gamePlayPersonTileData.getAmountWith().getValue() >= 0) {
                    GamePlayPersonTile.this.silohouette.setImageDrawable(gamePlayPersonTileData.getPerson().getImage());
                    if (android.os.Build.VERSION.SDK_INT >= 16) {
                        GamePlayPersonTile.this.amountHolder.setBackground(getResources().getDrawable(R.color.light_blue));
                    } else {
                        GamePlayPersonTile.this.amountHolder.setBackgroundDrawable(getResources().getDrawable(R.color.light_blue));
                    }
                    recordAngerForMission();
                } else if ((float) gamePlayPersonTileData.getTimeLeft() > (float) gamePlayPersonTileData.getExitTime() * (2f / 3f)) {
                    if (isConductorOwingChange()) {
                        GamePlayPersonTile.this.silohouette.setImageDrawable(gamePlayPersonTileData.getPerson().getImage());
                    }
                    if (android.os.Build.VERSION.SDK_INT >= 16) {
                        GamePlayPersonTile.this.amountHolder.setBackground(getResources().getDrawable(R.color.light_green));
                    } else {
                        GamePlayPersonTile.this.amountHolder.setBackgroundDrawable(getResources().getDrawable(R.color.light_green));
                    }
                    recordAngerForMission();
                } else if ((float) gamePlayPersonTileData.getTimeLeft() > (float) gamePlayPersonTileData.getExitTime() * (1f / 3f)) {
                    if (isConductorOwingChange()) {
                        GamePlayPersonTile.this.silohouette.setImageDrawable(gamePlayPersonTileData.getPerson().getImageImpatient());
                    }
                    if (android.os.Build.VERSION.SDK_INT >= 16) {
                        GamePlayPersonTile.this.amountHolder.setBackground(getResources().getDrawable(R.color.light_yellow));
                    } else {
                        GamePlayPersonTile.this.amountHolder.setBackgroundDrawable(getResources().getDrawable(R.color.light_yellow));
                    }
                    recordAngerForMission();
                } else {
                    if (isConductorOwingChange()) {
                        GamePlayPersonTile.this.silohouette.setImageDrawable(gamePlayPersonTileData.getPerson().getImageAngry());
                        if (!isAngerRecorded) {
                            silohouette.startAnimation(AnimationFactory.newWobbleAnimation());
                            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementNumberOfAngryPassengers(1);
                            isAngerRecorded = true;
                        }
                    }
                    if (android.os.Build.VERSION.SDK_INT >= 16) {
                        GamePlayPersonTile.this.amountHolder.setBackground(getResources().getDrawable(R.color.light_red));
                    } else {
                        GamePlayPersonTile.this.amountHolder.setBackgroundDrawable(getResources().getDrawable(R.color.light_red));
                    }
                }
            }

            private boolean isConductorOwingChange() {
                return (gamePlayPersonTileData.getAmountWith().getValue() < 0);
            }

        });
    }

    private void recordAngerForMission() {
        if (isAngerRecorded) {
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().decrementNumberOfAngryPassengers(1);
            isAngerRecorded = false;
        }
    }

    private void updateProgressBar() {
        progress.post(new Runnable() {
            public final String TAG = this.getClass().getSimpleName();

            @Override
            public void run() {
                progress.setProgress(gamePlayPersonTileData.getTimeLeft());
                if ((float) progress.getProgress() > (float) gamePlayPersonTileData.getExitTime() * (2f / 3f)) {
                    progress.getProgressDrawable().setColorFilter(getResources().getColor(R.color.light_green), PorterDuff.Mode.MULTIPLY);
                } else if ((float) progress.getProgress() > (float) gamePlayPersonTileData.getExitTime() * (1f / 3f)) {
                    progress.getProgressDrawable().setColorFilter(getResources().getColor(R.color.light_yellow), PorterDuff.Mode.MULTIPLY);
                } else {
                    progress.getProgressDrawable().setColorFilter(getResources().getColor(R.color.light_red), PorterDuff.Mode.MULTIPLY);
                }
            }
        });
    }


    protected void handleDebit() {
        if (gamePlayPersonTileData.getAmountToPay().isEmpty()) {
            Sound.playPassengerAlreadyPaidSfx();
        } else {
            amountWith.setTypeface(null, Typeface.NORMAL);
            Sound.playRetrievePassengerCashSfx();
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementTotalAmountCollected(gamePlayPersonTileData.getAmountWith().getValue());
            gamePlayFragment.getGamePlayTailView().incrementDenominationUnitCount(gamePlayPersonTileData.getAmountWith(), (short) 1);
            decrementAmountWith((short) (gamePlayPersonTileData.getAmountWith().getValue() + (gamePlayPersonTileData.getAmountWith().getValue() - gamePlayPersonTileData.getAmountToPay().getValue())));
            decrementAmountToPay(gamePlayPersonTileData.getAmountToPay().getValue());
            amountToPay.setTextColor(getResources().getColor(android.R.color.black));
            if (gamePlayPersonTileData.getAmountWith().getValue() == 0) {
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementExactFarePassengers(1);
                if (gamePlayPersonTileData.getPerson().getSex() == Sex.FEMALE) {
                    gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementNumberOfSettledFemales(1);
                } else {
                    gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementNumberOfSettledMales(1);
                }
            }
        }
    }

    protected void handleCredit() {
        if (!gamePlayPersonTileData.getAmountToPay().isEmpty()) {
            //can't give back change without collecting money first
            gamePlayFragment.getGamePlayTailView().doClearWalletFirstHint();
            Sound.playEmptySfx();
            return;
        }

        Sound.playReturnChangeSfx();
        int credit = gamePlayFragment.getGamePlayTailView().getGamePlayTailData().getSelectedAmount();
        incrementAmountWith((short) credit);
        gamePlayFragment.getGamePlayTailView().clearSelected();
        if (gamePlayPersonTileData.getAmountWith().getValue() > 0) {
            //punish player for giving more change than expected
            gamePlayFragment.getGamePlayHeaderView().decrementLife(5);
        }
        if (gamePlayPersonTileData.getAmountWith().getValue() >= 0 && !isSettledRecorded) {
            if (gamePlayPersonTileData.getPerson().getSex() == Sex.FEMALE) {
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementNumberOfSettledFemales(1);
            } else {
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementNumberOfSettledMales(1);
            }
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementTotalAmountPaidOut(credit);
            isSettledRecorded = true;
        }
    }

    @Override
    public void doGameUpdates(final GamePlayFragment gamePlayFragment, double delta) {
        updateLength += delta * GamePlayFragment.OPTIMAL_TIME;
        GamePlayHeaderData gamePlayHeaderData = gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData();
        decrementTimeRemaining((short) 1);
        if (updateLength >= 1000000000) {//for each second
            if (gamePlayPersonTileData.getAmountToPay().isEmpty() && gamePlayPersonTileData.getAmountWith().getValue() >= 0 && gamePlayPersonTileData.getTimeLeft() > 0) {
                //Passenger has been settled, increment points
                incrementPointsBasedOnDifficulty();
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().setScore(gamePlayHeaderData.getPoints());
            } else if (gamePlayPersonTileData.getAmountWith().getValue() < 0 && gamePlayPersonTileData.getTimeLeft() <= 0) {
                //passenger not yet settled, decrement life
                gamePlayFragment.getGamePlayHeaderView().decrementLife(gamePlayFragment.getGamePlayFragmentData().getUnsettledPassengerHealthDeduction());
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().setHealth(gamePlayHeaderData.getLife());

            }
            updateLength = 0;
        }

        if (gamePlayPersonTileData.getTimeLeft() <= 0) {
            if (gamePlayPersonTileData.getAmountToPay().getValue() > 0) {
                //passenger left without paying
                gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementEscapedPassengers(1);
                gamePlayFragment.getGamePlayHeaderView().decrementLife(5);
            }
        }

    }

    @Override
    public void doGameRender(final GamePlayFragment gamePlayFragment) {
        final TextView pointsTextView = gamePlayFragment.getGamePlayHeaderView().getPointsTextView();
        final TextView healthTextView = gamePlayFragment.getGamePlayHeaderView().getHealthTextView();
        final GamePlayHeaderData gamePlayHeaderData = gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData();
        final ImageView healthTextImage = gamePlayFragment.getGamePlayHeaderView().getHealthTextImage();

        if (gamePlayPersonTileData.getAmountToPay().isEmpty() && gamePlayPersonTileData.getAmountWith().getValue() >= 0 && gamePlayPersonTileData.getTimeLeft() > 0) {
            //Passenger has been settled, increment points
            pointsTextView.post(new Runnable() {
                @Override
                public void run() {
                    pointsTextView.setText(String.valueOf(gamePlayHeaderData.getPoints()));
                }
            });
        } else if (gamePlayPersonTileData.getAmountWith().getValue() < 0 && gamePlayPersonTileData.getTimeLeft() <= 0) {
            //passenger not yet settled, play patience exhausted sound
            if (gamePlayPersonTileData.getTimeLeft() == 0) {
                Sound.playPassengerPatienceExhaustedSfx();
            }
            healthTextView.post(new Runnable() {
                @Override
                public void run() {
                    healthTextView.setText(String.valueOf(gamePlayHeaderData.getLife()));
                }
            });
            if ((float) gamePlayHeaderData.getLife() > (float) GamePlayHeaderData.MAX_LIFE * 2f / 3f) {
                //good health
            } else if ((float) gamePlayHeaderData.getLife() > (float) GamePlayHeaderData.MAX_LIFE * 1f / 3f) {
                healthTextImage.post(new Runnable() {
                    @Override
                    public void run() {
                        healthTextImage.startAnimation(AnimationFactory.newWobbleAnimation());
                    }
                });
            } else {
                healthTextImage.post(new Runnable() {
                    @Override
                    public void run() {
                        healthTextImage.startAnimation(AnimationFactory.newWobbleAnimation());
                    }
                });
            }

        } else if (gamePlayPersonTileData.getTimeLeft() <= 0) {
            this.post(new Runnable() {
                @Override
                public void run() {
                    Sound.playPassengerExitSfx();
                    recordAngerForMission();//decrements angry passenger if was recorded as angry before.

                    //put in a new passenger
                    setGamePlayPersonTileData(getGamePlayPersonTileData().getPerson().getSex() == Sex.MALE
                            ? gamePlayFragment.getPersonTileAdapter().getRandomFemaleGamePlayPersonTileData() : gamePlayFragment.getPersonTileAdapter().getRandomMaleGamePlayPersonTileData());

                    //health animtaiotn
                    healthTextView.setText(String.valueOf(gamePlayHeaderData.getLife()));
                    if ((float) gamePlayHeaderData.getLife() > (float) GamePlayHeaderData.MAX_LIFE * (float) (2f / 3f)) {
                        //good health
                    } else if ((float) gamePlayHeaderData.getLife() > (float) GamePlayHeaderData.MAX_LIFE * (float) (1f / 3f)) {
                        healthTextImage.startAnimation(AnimationFactory.newWobbleAnimation());
                    } else {
                        healthTextImage.startAnimation(AnimationFactory.newWobbleAnimation());
                    }
                }
            });
        }
        updateBackgroundImage();
        updateProgressBar();
    }


    public void onClick(View v) {
        if (gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().isPaused()) {
            return;
        }
        boolean isCredit = gamePlayFragment.getGamePlayTailView().getGamePlayTailData().getSelectedAmount() > 0;
        if (isCredit) {
            handleCredit();
        } else {
            handleDebit();
        }
    }


}
