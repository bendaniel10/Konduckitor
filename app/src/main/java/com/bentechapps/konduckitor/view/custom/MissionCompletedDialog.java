package com.bentechapps.konduckitor.view.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.activity.fragments.HomePageFragement;
import com.bentechapps.konduckitor.activity.fragments.ShopItemFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.adapter.StartMissionDialogAdapter;

/**
 * Created by Daniel on 5/17/2015.
 */
public class MissionCompletedDialog extends Dialog implements View.OnClickListener {
    private final GamePlayFragment gamePlayFragment;
    private ApplicationData appData;
    private ListView missionList;
    private ImageButton restartButton;
    private TextView score;
    private TextView money;
    private ImageButton homeButton;
    private ImageButton nextMissionButton;
    private ImageButton shopButton;

    public MissionCompletedDialog(Context context, GamePlayFragment gamePlayFragment) {
        super(context);
        this.gamePlayFragment = gamePlayFragment;
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
        Sound.playButtonClickSfx();
        Sound.stopGameOverMusic();
        switch (v.getId()) {
            case R.id.restart_button:
                handleRestartButton();
                break;
            case R.id.home_button:
                handleHomeButton();
                break;
            case R.id.next_mission_button:
                handleNextMission();
                break;
            case R.id.shop_button:
                handleShop();
                break;
        }
    }

    private void handleHomeButton() {
        HomePageFragement homePageFragement = new HomePageFragement();
        ((MainActivity) gamePlayFragment.getActivity()).switchFragments(R.id.fragment_container, homePageFragement);
    }

    private void handleRestartButton() {
        GamePlayFragment.handleRestartAndNextMissionInit(gamePlayFragment);
        gamePlayFragment.initializeGame();
    }

    private void handleNextMission() {
        GamePlayFragmentData gamePlayFragmentData = gamePlayFragment.getGamePlayFragmentData();
        gamePlayFragmentData.setCurrentMission(Mission.getMission(gamePlayFragment));
        gamePlayFragmentData.setCurrentLevel(Level.getCurrentLevel(getContext()));
        GamePlayFragment.handleRestartAndNextMissionInit(gamePlayFragment);
        gamePlayFragment.initializeGame();
    }

    private void handleShop() {
        ShopItemFragment shopItemFragment = new ShopItemFragment();
        ((MainActivity) gamePlayFragment.getActivity()).switchFragments(R.id.fragment_container, shopItemFragment);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appData = ApplicationData.getInstance();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mission_completed_dialog);
        missionList = (ListView) findViewById(R.id.mission_list);
        restartButton = (ImageButton) findViewById(R.id.restart_button);
        score = (TextView) findViewById(R.id.score);
        money = (TextView) findViewById(R.id.money);
        homeButton = (ImageButton) findViewById(R.id.home_button);
        nextMissionButton = (ImageButton) findViewById(R.id.next_mission_button);
        shopButton = (ImageButton) findViewById(R.id.shop_button);


        setCanceledOnTouchOutside(false);
        setCancelable(false);

        missionList.setAdapter(new StartMissionDialogAdapter(gamePlayFragment.getGamePlayFragmentData().getCurrentMission(), getContext()).setIsShowFullDetail(true));
        restartButton.setOnClickListener(this);
        nextMissionButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        shopButton.setOnClickListener(this);

        int scoreInt = gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().getPoints();
        long playTimeInt = gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().getPlayTime();
        long moneyInt = (playTimeInt * scoreInt) / 100;

        if (isNewHighScore(scoreInt)) {
            doHighScoreInit();
        }

        money.setText(String.format("Money: %s", moneyInt));
        score.setText(String.format("Score: %s", scoreInt));

        if (appData.getCurrentMission() == 1 && appData.getCurrentLevel() != 1) {//was promoted to a new level
            nextMissionButton.setVisibility(View.GONE);
        }
        Sound.playGameOverMusic();
    }

    private void doHighScoreInit() {
        Sound.playApplauseSfx();
        ((ImageView) findViewById(R.id.score_image)).setImageDrawable(getContext().getResources().getDrawable(R.drawable.highscore));
    }

    private boolean isNewHighScore(int scoreInt) {
        return scoreInt > appData.getHighScore();
    }
}
