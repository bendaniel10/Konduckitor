package com.bentechapps.konduckitor.view.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.adapter.PauseDefaultPowerUpSpinnerAdapter;
import com.bentechapps.konduckitor.view.adapter.StartMissionDialogAdapter;

/**
 * Created by Daniel on 5/16/2015.
 */
public class PauseGameDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private final Context context;
    private final Mission mission;
    private ListView missionList;
    private ImageButton soundButton;
    private ImageButton musicButton;
    private ImageButton restartButton;
    private ImageButton homeButton;
    private ImageButton resumeButton;
    private ApplicationData appData;
    private Spinner defaultPowerUp;

    public PauseGameDialog(Context context, Mission mission) {
        super(context);
        this.context = context;
        this.mission = mission;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sound_button:
                handleSoundButton();
                break;
            case R.id.music_button:
                handleMusicButton();
                break;
            case R.id.restart_button:
                handleRestartButton();
                break;
            case R.id.home_button:
                handleHomeButton();
                break;
            case R.id.resume_button:
                handleResumeButton();
                break;
        }
    }

    private void handleMusicButton() {
        appData.setMusic(!appData.isMusic());

        if (!appData.isMusic()) {
            Sound.stopGamePlayMusic();
            Sound.stopDrivingByMusic();
            musicButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.music_off));
        } else {
            Sound.playGamePlayMusic();
            Sound.playDrivingByMusic();
            musicButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.music));
        }

    }

    private void handleRestartButton() {
        //restart fragment hack :-)
        this.dismiss();
        GamePlayFragment gamePlayFragment = (GamePlayFragment) ((MainActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        GamePlayFragment.handleRestartAndNextMissionInit(gamePlayFragment);
        gamePlayFragment.initializeGame();
    }

    private void handleHomeButton() {
        this.dismiss();
        GamePlayFragment gamePlayFragment = (GamePlayFragment) ((MainActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        gamePlayFragment.getActivity().getSupportFragmentManager().popBackStack();
        gamePlayFragment.getActivity().getSupportFragmentManager().popBackStack();
        gamePlayFragment.getActivity().getSupportFragmentManager().popBackStack();
    }

    private void handleResumeButton() {
        this.dismiss();
        ((GamePlayFragment) ((MainActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_container)).getGamePlayHeaderView().handlePause();
    }

    private void handleSoundButton() {
        appData.setSfx(!appData.isSfx());

        if (!appData.isSfx()) {
            soundButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.sound_off));
        } else {
            soundButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.sound));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appData = ApplicationData.getInstance(getContext());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pause_game_dialog);
        missionList = (ListView) findViewById(R.id.mission_list);
        soundButton = (ImageButton) findViewById(R.id.sound_button);
        musicButton = (ImageButton) findViewById(R.id.music_button);
        restartButton = (ImageButton) findViewById(R.id.restart_button);
        homeButton = (ImageButton) findViewById(R.id.home_button);
        resumeButton = (ImageButton) findViewById(R.id.resume_button);
        defaultPowerUp = (Spinner) findViewById(R.id.default_power_up_spinner);
        defaultPowerUp.setAdapter(new PauseDefaultPowerUpSpinnerAdapter(getContext(), R.layout.pause_power_up_item,
                R.id.text, (ShopItem[]) ShopItem.list(getContext()).toArray()));

        setCanceledOnTouchOutside(false);
        setCancelable(false);

        if (((GamePlayFragment) ((MainActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_container)).getGamePlayFragmentData().isMissionMode()) {
            missionList.setAdapter(new StartMissionDialogAdapter(mission, getContext()).setIsShowFullDetail(true));
        }

        defaultPowerUp.setSelection(appData.getDefaultPowerUp());

        defaultPowerUp.setOnItemSelectedListener(this);

        soundButton.setOnClickListener(this);
        musicButton.setOnClickListener(this);
        restartButton.setOnClickListener(this);
        resumeButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);

        if (!appData.isMusic()) {
            musicButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.music_off));
        } else {
            musicButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.music));
        }

        if (!appData.isSfx()) {
            soundButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.sound_off));
        } else {
            soundButton.setImageDrawable(getContext().getResources().getDrawable(R.drawable.sound));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.default_power_up_spinner:
                handlePowerUp();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void handlePowerUp() {
        GamePlayFragment gamePlayFragment = (GamePlayFragment) ((MainActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        appData.setDefaultPowerUp(defaultPowerUp.getSelectedItemPosition());
        gamePlayFragment.getGamePlayTailView().getGamePlayTailData().setDefaultShopItem(ShopItem.list(context).get(defaultPowerUp.getSelectedItemPosition()));
        gamePlayFragment.getGamePlayTailView().updatePowerUpView();
    }
}
