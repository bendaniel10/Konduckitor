package com.bentechapps.konduckitor.activity.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.animation.AnimationFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomePageFragement.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePageFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragement extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;
    private Button playButton;
    private ImageButton helpButton;
    private Button settingsButton;
    private ImageButton soundButton;
    private MainActivity mainActivity;
    private TextView highScoreTextView;
    private ImageButton shopButton;
    private ApplicationData appData;
    private TextView repuationTextView;
    private Button missionButton;
    private ImageButton musicButton;
    private ImageView quickPlayImage;
    private ImageView settingsImage;
    private ImageView missionImage;
    private ImageButton rateButton;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragement newInstance(String param1, String param2) {
        HomePageFragement fragment = new HomePageFragement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomePageFragement() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_home_page, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appData = ApplicationData.getInstance(getActivity());
        this.mainActivity = (MainActivity) getActivity();
        playButton = (Button) getView().findViewById(R.id.quick_play_button);
        missionButton = (Button) getView().findViewById(R.id.mission_button);
        helpButton = (ImageButton) getView().findViewById(R.id.help_button);
        settingsButton = (Button) getView().findViewById(R.id.settings_button);
        rateButton = (ImageButton) getView().findViewById(R.id.rate_button);
        soundButton = (ImageButton) getView().findViewById(R.id.sound_button);
        musicButton = (ImageButton) getView().findViewById(R.id.music_button);
        shopButton = (ImageButton) getView().findViewById(R.id.shop_button);
        quickPlayImage = (ImageView) getView().findViewById(R.id.quick_play_image);
        missionImage = (ImageView) getView().findViewById(R.id.mission_image);
        settingsImage = (ImageView) getView().findViewById(R.id.settings_image);
        highScoreTextView = (TextView) getView().findViewById(R.id.highscore_text);

        playButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        soundButton.setOnClickListener(this);
        shopButton.setOnClickListener(this);
        missionButton.setOnClickListener(this);
        musicButton.setOnClickListener(this);
        quickPlayImage.setOnClickListener(this);
        settingsImage.setOnClickListener(this);
        missionImage.setOnClickListener(this);
        rateButton.setOnClickListener(this);

        if (!appData.isMusic()) {
            musicButton.setImageDrawable(getResources().getDrawable(R.drawable.music_off));
        } else {
            musicButton.setImageDrawable(getResources().getDrawable(R.drawable.music));
        }

        if (!appData.isSfx()) {
            soundButton.setImageDrawable(getResources().getDrawable(R.drawable.sound_off));
        } else {
            soundButton.setImageDrawable(getResources().getDrawable(R.drawable.sound));
        }

        highScoreTextView.setText(String.valueOf(appData.getHighScore()));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        Sound.playButtonClickSfx();
        switch (v.getId()) {
            case R.id.settings_button:
            case R.id.settings_image:
                handleSettings();
                break;
            case R.id.help_button:
                handleHelp();
                break;
            case R.id.music_button:
                handleMusic();
                break;
            case R.id.sound_button:
                handleSound();
                break;
            case R.id.quick_play_button:
            case R.id.quick_play_image:
                handlePlay();
                break;
            case R.id.shop_button:
                handleShop();
                break;
            case R.id.mission_button:
            case R.id.mission_image:
                handleMission();
                break;
            case R.id.rate_button:
                handleRate();
                break;
        }
    }

    private void handleRate() {
        final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
//        appData.incrementCalmDownCount(1000);
//        appData.incrementComedianCount(1000);
//        appData.incrementRegenerateCount(1000);
//        appData.incrementSplitLargestChangeCount(1000);
//        appData.incrementGraGraCount(1000);

    }

    private void handleMission() {
        startFragmentAfterAnimation(new ChooseLevelFragment(), missionImage);
    }

    private void handleSound() {
        appData.setSfx(!appData.isSfx());
        if (!appData.isSfx()) {
            soundButton.setImageDrawable(getResources().getDrawable(R.drawable.sound_off));
        } else {
            Sound.playButtonClickSfx();
            soundButton.setImageDrawable(getResources().getDrawable(R.drawable.sound));
        }
    }

    private void handleShop() {
        ShopItemFragment shopItemFragment = new ShopItemFragment();
        mainActivity.switchFragmentsAddToBackStack(shopItemFragment);
    }

    private void handlePlay() {
        GamePlayFragment gamePlayFragment = appData.getPlayTutorial() == 1 ? new GamePlayTutorialFragment() : new GamePlayFragment();
        GamePlayFragmentData gamePlayFragmentData = new GamePlayFragmentData(getActivity());
        gamePlayFragment.setGamePlayFragmentData(gamePlayFragmentData);
        mainActivity.setGamePlayFragment(gamePlayFragment);

        startFragmentAfterAnimation(gamePlayFragment, quickPlayImage);
    }

    private void handleMusic() {
        appData.setMusic(!appData.isMusic());
        if (!appData.isMusic()) {
            Sound.stopGamePlayMusic();
            musicButton.setImageDrawable(getResources().getDrawable(R.drawable.music_off));
        } else {
            Sound.playGamePlayMusic();
            musicButton.setImageDrawable(getResources().getDrawable(R.drawable.music));
        }
    }

    private void handleHelp() {
        GamePlayFragment gamePlayFragment = new GamePlayTutorialFragment();
        GamePlayFragmentData gamePlayFragmentData = new GamePlayFragmentData(getActivity());
        gamePlayFragment.setGamePlayFragmentData(gamePlayFragmentData);
        mainActivity.setGamePlayFragment(gamePlayFragment);

        mainActivity.switchFragmentsAddToBackStack(gamePlayFragment);
    }

    private void startFragmentAfterAnimation(final Fragment fragment, View animatingView) {
        AnimationSet animationSet = AnimationFactory.newRotateAnimation();
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mainActivity.switchFragmentsAddToBackStack(fragment);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animatingView.startAnimation(animationSet);
    }

    private void handleSettings() {
        startFragmentAfterAnimation(new PreferenceFragment(), settingsImage);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
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
