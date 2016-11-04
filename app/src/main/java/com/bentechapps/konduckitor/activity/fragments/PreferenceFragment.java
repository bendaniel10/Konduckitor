package com.bentechapps.konduckitor.activity.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.adapter.PreferenceSpinnerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferenceFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {


    private Switch soundSwitch;
    private Switch sfxSwitch;
    private Spinner difficultySpinner;
    private Spinner defaultPowerUp;
    private ApplicationData appData;
    private View creditsTextView;

    public PreferenceFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preference, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appData = ApplicationData.getInstance(getActivity());
        sfxSwitch = (Switch) getView().findViewById(R.id.sfx_switch);
        soundSwitch = (Switch) getView().findViewById(R.id.music_switch);
        difficultySpinner = (Spinner) getView().findViewById(R.id.difficulty_spinner);
        defaultPowerUp = (Spinner) getView().findViewById(R.id.default_power_up_spinner);

        defaultPowerUp.setAdapter(new PreferenceSpinnerAdapter(getActivity(), R.layout.shop_spinner_item,
                R.id.text, (ShopItem[]) ShopItem.list(getActivity()).toArray()));
        difficultySpinner.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, new String[]{"Easy", "Normal", "Hard"}));
        creditsTextView = getView().findViewById(R.id.credits);


        sfxSwitch.setOnCheckedChangeListener(this);
        soundSwitch.setOnCheckedChangeListener(this);
        defaultPowerUp.setOnItemSelectedListener(this);
        difficultySpinner.setOnItemSelectedListener(this);
        creditsTextView.setOnClickListener(this);

        sfxSwitch.setChecked(appData.isSfx());
        soundSwitch.setChecked(appData.isMusic());
        defaultPowerUp.setSelection(appData.getDefaultPowerUp());
        difficultySpinner.setSelection(appData.getDifficulty());
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
        } else {
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
    }

    private void handleDifficulty() {
        appData.setDifficulty(difficultySpinner.getSelectedItemPosition());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.difficulty_spinner:
                handleDifficulty();
                break;
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
            case R.id.credits:
                handleCredits();
                break;
        }
    }

    private void handleCredits() {
        ((MainActivity) getActivity()).switchFragmentsAddToBackStack(R.id.fragment_container, new CreditsFragment());
    }

    @Override
    public void onResume() {
        super.onResume();
        Sound.playGamePlayMusic();
    }

    @Override
    public void onPause() {
        super.onPause();
        Sound.stopGamePlayMusic();
    }
}
