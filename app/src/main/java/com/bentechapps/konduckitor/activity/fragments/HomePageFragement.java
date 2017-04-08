package com.bentechapps.konduckitor.activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bentech.android.appcommons.constants.alert.AlertLevel;
import com.bentech.android.appcommons.fragment.AppCommonsFragment;
import com.bentech.android.appcommons.utils.CurrencyUtils;
import com.bentech.android.appcommons.utils.DrawableUtils;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.sound.Sound;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePageFragement extends AppCommonsFragment {
    @BindView(R.id.moneyTxt)
    TextView moneyTxt;
    @BindView(R.id.soundButton)
    ImageButton soundButton;

    private ApplicationData appData;

    public HomePageFragement() {
        // Required empty public constructor
    }

    @OnClick(R.id.soundButton)
    void soundButtonAction() {

        appData.setSfx(!appData.isSfx());
        appData.setMusic(appData.isSfx());
        if (!appData.isSfx()) {
            Sound.stopGamePlayMusic();
            soundButton.setImageDrawable(DrawableUtils.getDrawable(appCommonsActivity, R.drawable.sound_off));
        } else {
            Sound.playButtonClickSfx();
            Sound.playGamePlayMusic();
            soundButton.setImageDrawable(DrawableUtils.getDrawable(appCommonsActivity, R.drawable.sound));
        }
    }

    @OnClick(R.id.playButton)
    void playButtonAction() {
        appCommonsActivity.switchFragmentsAddToBackStack(R.id.fragment_container, new ChooseLevelFragment());

    }

    @OnClick(R.id.connectBtn)
    void connectBtnAction(View view) {
        appCommonsActivity.showShortSnackBar(view, R.string.label_not_available, AlertLevel.WARN);
    }

    @OnClick(R.id.shopButton)
    void shopButtonAction() {
        appCommonsActivity.switchFragmentsAddToBackStack(R.id.fragment_container, new StoreFragment());
    }

    @OnClick(R.id.settingsBtn)
    void psettingsBtnAction() {
        appCommonsActivity.switchFragmentsAddToBackStack(R.id.fragment_container, new PreferenceFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_new_home_page, container, false);
        ButterKnife.bind(this, rootView);

        initView();
        return rootView;
    }

    private void initView() {

        appData = ApplicationData.getInstance();


        if (appData.isMusic() && appData.isSfx()) {
            soundButton.setImageDrawable(DrawableUtils.getDrawable(appCommonsActivity, R.drawable.sound_off));
        } else {
            soundButton.setImageDrawable(DrawableUtils.getDrawable(appCommonsActivity, R.drawable.sound));
        }

        moneyTxt.setText(CurrencyUtils.formatToCurrencyWithSymbol((double) appData.getReputation(), Constants.CURRENCY_SYMBOL));
    }


    @Override
    public void onPause() {
        super.onPause();
        Sound.stopGamePlayMusic();
    }

    @Override
    public void onResume() {
        super.onResume();
        Sound.playGamePlayMusic();
        Sound.playCarStartingSfx();
    }

}
