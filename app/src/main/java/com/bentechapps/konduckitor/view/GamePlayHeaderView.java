package com.bentechapps.konduckitor.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.data.GamePlayHeaderData;
import com.bentechapps.konduckitor.engine.GameLoopItem;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.animation.AnimationFactory;
import com.bentechapps.konduckitor.view.custom.MissionFailedDialog;
import com.bentechapps.konduckitor.view.custom.PauseGameDialog;

import java.util.Random;

/**
 * Created by BenTech on 2/1/2015.
 */
public class GamePlayHeaderView extends RelativeLayout implements View.OnClickListener, GameLoopItem {
    protected final MainActivity mainActivity;
    private final GamePlayFragment gamePlayFragment;
    protected ImageButton pauseButton;
    private TextView pointsTextView;
    protected TextView playTimeTextView;
    protected TextView healthTextView;
    protected GamePlayHeaderData gamePlayHeaderData;
    private String gameOverComment[] = {"Game too hard? Adjust difficulty in the settings menu.", "Tip: the quicker you settle the passengers, the higher your score", "Tip: the regenerate power up increases your health.",
            "Never get caught without change. The Split power up splits your largest change for you.", "Doroboss! your eye sharp well well!"};
    private String badComment[] = {"Power ups make you last longer", "The bus driver isn't impressed with your (lack of) skills", "You no sabi this work, we lost money", "We lost money yet again!", "You're fired!!",
            "Passengers in red will deplete your health.", "Learn to collect money from all the passengers before they leave", "Keep trying, you'll get better"};
    protected ApplicationData appData;
    private ImageView healthTextImage;
    private int resetTime;
    private double updateLength;

    public GamePlayHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mainActivity = (MainActivity) context;
        this.gamePlayFragment = mainActivity.getGamePlayFragment();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.game_play_header_view, this, true);
        init();
    }

    private void init() {
        appData = ApplicationData.getInstance(this.mainActivity);
        healthTextView = (TextView) findViewById(R.id.health);
        pauseButton = (ImageButton) findViewById(R.id.pause_button);
        pointsTextView = (TextView) findViewById(R.id.points);
        playTimeTextView = (TextView) findViewById(R.id.run_time);
        healthTextImage = (ImageView) findViewById(R.id.health_image);

        pauseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pause_button:
                handlePause();
                break;
        }
    }

    public void updatePauseView() {
        pauseButton.post(new Runnable() {
            @Override
            public void run() {
                if (gamePlayHeaderData.isPaused()) {
                    pauseButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_play));
                } else {
                    pauseButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_media_pause));
                }
            }
        });

    }

    public void handlePause() {
        gamePlayHeaderData.setPaused(!gamePlayHeaderData.isPaused());
        updatePauseView();
        if (gamePlayHeaderData.isPaused()) {
            PauseGameDialog pauseGameDialog = new PauseGameDialog(gamePlayFragment.getActivity(), gamePlayFragment.getGamePlayFragmentData().getCurrentMission());
            pauseGameDialog.show();
        }
    }

    public TextView getHealthTextView() {
        return healthTextView;
    }

    public ImageView getHealthTextImage() {
        return healthTextImage;
    }

    protected void updateLifeTextView() {
        healthTextView.post(new Runnable() {
            @Override
            public void run() {
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

    public GamePlayHeaderData getGamePlayHeaderData() {
        return gamePlayHeaderData;
    }

    public void setGamePlayHeaderData(GamePlayHeaderData gamePlayHeaderData) {
        this.gamePlayHeaderData = gamePlayHeaderData;
        updatePauseView();
        updateLifeTextView();
        pointsTextView.setText(String.valueOf(gamePlayHeaderData.getPoints()));
        playTimeTextView.setText(String.valueOf(gamePlayHeaderData.getPlayTime()));
    }

    public void incrementPlayTime(int offset) {
        gamePlayHeaderData.incrementPlayTime(offset);
    }

    public void incrementPoints(int offset) {
        gamePlayHeaderData.incrementPoints(offset);
    }

    public void incrementLife(int offset) {
        if (gamePlayHeaderData.getLife() < GamePlayHeaderData.MAX_LIFE) {
            gamePlayHeaderData.incrementLife(offset);
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementHealth(offset);
        }
    }


    public TextView getPointsTextView() {
        return pointsTextView;
    }

    /**
     * Decrements life after GamePlayFragment.GAME_LOOP_SLEEP_TIME calls to this method if the offset is one. else decrements immediately
     *
     * @param offset
     */
    public void decrementLife(int offset) {
        if (gamePlayHeaderData.getLife() <= 0) {
            doGameOver();
            return;
        }
        gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().decrementHealth(offset);
        gamePlayHeaderData.decrementLife(offset);
    }

    public void doGameOver() {
        gamePlayHeaderData.setPaused(true);
        pauseButton.post(new Runnable() {
            @Override
            public void run() {
                if (!gamePlayHeaderData.isSaved()) {
                    saveGameData();
//                    mainActivity.getGamePlayFragment().getLoop().cancel(true);
//                    mainActivity.getGamePlayFragment().setLoop(null);
                    MissionFailedDialog missionFailedDialog = new MissionFailedDialog(mainActivity, mainActivity.getGamePlayFragment());
                    missionFailedDialog.show();
                }
                gamePlayHeaderData.setIsSaved(true);
            }
        });
    }

    private void saveGameData() {
        if (mainActivity.getGamePlayFragment().getGamePlayFragmentData().isMissionMode()) {
            appData.incrementReputation((int) ((gamePlayHeaderData.getPlayTime() * gamePlayHeaderData.getPoints()) / 400));
        } else {
            appData.incrementReputation((int) ((gamePlayHeaderData.getPlayTime() * gamePlayHeaderData.getPoints()) / 100));
        }
        if (appData.getHighScore() < gamePlayHeaderData.getPoints()) {
            appData.setHighScore(gamePlayHeaderData.getPoints());
            Sound.playApplauseSfx();
        }
    }

    private String getGameOverComment() {
        Random random = new Random();
        GamePlayFragmentData gamePlayFragmentData = mainActivity.getGamePlayFragment().getGamePlayFragmentData();
        if (gamePlayHeaderData.getPoints() > 1000) {
            return gameOverComment[random.nextInt(gameOverComment.length)];
        } else {
            return badComment[random.nextInt(badComment.length)];
        }
    }

    public ImageButton getPauseButton() {
        return pauseButton;
    }

    @Override
    public void doGameUpdates(GamePlayFragment gamePlayFragment, double delta) {
        updateLength += delta * GamePlayFragment.OPTIMAL_TIME;
        if(updateLength >= 1000000000) {//for each second
            incrementPlayTime(1);
            gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().setGamePlayTime(gamePlayHeaderData.getPlayTime());
            updateLength = 0;
        }
    }

    @Override
    public void doGameRender(final GamePlayFragment gamePlayFragment) {
        playTimeTextView.post(new Runnable() {
            @Override
            public void run() {
                playTimeTextView.setText(String.valueOf(gamePlayHeaderData.getPlayTime()));
                updateLifeTextView();
            }
        });
    }
}
