package com.bentechapps.konduckitor.activity.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.adapter.PreferenceSpinnerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamePlayPauseFragment extends DialogFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {


    private Switch soundSwitch;
    private Switch sfxSwitch;
    private Spinner defaultPowerUp;
    private ApplicationData appData;
    private Button homeButton;
    private Button resumeGameButton;
    private GamePlayFragment gamePlayFragment;
    private Button skipTutorialButton;

    public GamePlayPauseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().setTitle("Game Paused");
        setCancelable(false);
        return inflater.inflate(R.layout.fragment_game_play_pause, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gamePlayFragment = ((MainActivity) getActivity()).getGamePlayFragment();
        appData = ApplicationData.getInstance(getActivity());
        sfxSwitch = (Switch) getView().findViewById(R.id.sfx_switch);
        soundSwitch = (Switch) getView().findViewById(R.id.music_switch);
        defaultPowerUp = (Spinner) getView().findViewById(R.id.default_power_up_spinner);

        defaultPowerUp.setAdapter(new PreferenceSpinnerAdapter(getActivity(), R.layout.shop_spinner_item,
                R.id.text, (ShopItem[]) ShopItem.list(getActivity()).toArray()));
        homeButton = (Button) getView().findViewById(R.id.homeButton);
         skipTutorialButton = (Button) getView().findViewById(R.id.skipTutorial);
        resumeGameButton = (Button) getView().findViewById(R.id.resumeGameButton);

        sfxSwitch.setOnCheckedChangeListener(this);
        soundSwitch.setOnCheckedChangeListener(this);
        defaultPowerUp.setOnItemSelectedListener(this);
        homeButton.setOnClickListener(this);
        resumeGameButton.setOnClickListener(this);
        skipTutorialButton.setOnClickListener(this);

        sfxSwitch.setChecked(appData.isSfx());
        soundSwitch.setChecked(appData.isMusic());
        defaultPowerUp.setSelection(appData.getDefaultPowerUp());


        if(gamePlayFragment instanceof GamePlayTutorialFragment) {
            homeButton.setVisibility(View.GONE);
        } else {
            skipTutorialButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sfx_switch:
                handleSfx(isChecked);
                break;
            case R.id.music_switch:
                handleMusic(isChecked);
                break;
        }
    }

    private void handleMusic(boolean isChecked) {
        appData.setMusic(isChecked);
        if (isChecked) {
            Sound.playGamePlayMusic();
            Sound.playDrivingByMusic();
        } else {
            Sound.stopDrivingByMusic();
            Sound.stopGamePlayMusic();
        }
    }

    private void handleSfx(boolean isChecked) {
        appData.setSfx(isChecked);
        if (isChecked) {
            Sound.playButtonClickSfx();
        }
    }

    private void handlePowerUp() {
        appData.setDefaultPowerUp(defaultPowerUp.getSelectedItemPosition());
        gamePlayFragment.getGamePlayTailView().getGamePlayTailData().setDefaultShopItem(ShopItem.list(getActivity()).get(defaultPowerUp.getSelectedItemPosition()));
        gamePlayFragment.getGamePlayTailView().updatePowerUpView();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeButton:
                handleHome();
                break;
            case R.id.resumeGameButton:
                handleResumeGame();
                break;
            case R.id.skipTutorial:
                handleSkipTutorial();
        }
    }

    private void handleSkipTutorial() {
        if(gamePlayFragment instanceof GamePlayTutorialFragment) {
            Sound.playButtonClickSfx();
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Exit tutorial? You can always replay this tutorial from the help menu.");
            builder.setTitle("Confirm exit");
            builder.setPositiveButton("Exit Tutorial", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getDialog().dismiss();
                    appData.setPlayTutorial(0);
                    (getActivity()).getSupportFragmentManager().popBackStack();
                }
            });
            builder.setNegativeButton("Stay in game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().setPaused(!gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().isPaused());
                    gamePlayFragment.getGamePlayHeaderView().updatePauseView();
                    getDialog().dismiss();
                }
            });
            builder.show();
        }
    }

    private void handleResumeGame() {
        Sound.playButtonClickSfx();
//        (getActivity()).getSupportFragmentManager().popBackStack();
        getDialog().dismiss();
        gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().setPaused(false);
        gamePlayFragment.getGamePlayHeaderView().updatePauseView();
    }

    private void handleHome() {
        Sound.playButtonClickSfx();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Progress will not be saved, continue?");
        builder.setTitle("Confirm exit");
        builder.setPositiveButton("Continue to home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getDialog().dismiss();
                (getActivity()).getSupportFragmentManager().popBackStack();
//                (getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        builder.setNegativeButton("Stay in game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().setPaused(!gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().isPaused());
                gamePlayFragment.getGamePlayHeaderView().updatePauseView();
                dialog.dismiss();
                getDialog().dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Sound.playGamePlayMusic();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
