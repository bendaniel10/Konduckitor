package com.bentechapps.konduckitor.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.sound.Sound;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link GameOverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameOverFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private String point;
    private String runtime;

    private TextView reputationTextView;
    private TextView commentsTextView;
    private TextView pointsTextView;
    private TextView runtimeTextView;
    private String reputation;
    private String comments;
    private Button replayButton;
    private Button homeButton;
    private ApplicationData appData;
    private MainActivity mainActivity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param runTime Parameter 1.
     * @param points  Parameter 2.
     * @return A new instance of fragment GameOverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameOverFragment newInstance(String runTime, String points, String reputation, String comments) {
        GameOverFragment fragment = new GameOverFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, runTime);
        args.putString(ARG_PARAM2, points);
        args.putString(ARG_PARAM3, reputation);
        args.putString(ARG_PARAM4, comments);
        fragment.setArguments(args);
        return fragment;
    }

    public GameOverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            runtime = getArguments().getString(ARG_PARAM1);
            point = getArguments().getString(ARG_PARAM2);
            reputation = getArguments().getString(ARG_PARAM3);
            comments = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_over, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appData = ApplicationData.getInstance();
        this.mainActivity = (MainActivity) getActivity();
        runtimeTextView = (TextView) getView().findViewById(R.id.runTimeTextView);
        pointsTextView = (TextView) getView().findViewById(R.id.scoreTextView);
        commentsTextView = (TextView) getView().findViewById(R.id.commentTextView);
        reputationTextView = (TextView) getView().findViewById(R.id.reputationTextView);
        replayButton = (Button) getView().findViewById(R.id.replayButton);
        homeButton = (Button) getView().findViewById(R.id.homeButton);

        runtimeTextView.setText(runtime);
        pointsTextView.setText(point);
        commentsTextView.setText(comments);
        reputationTextView.setText(reputation);
        replayButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeButton:
                handleHome();
                break;
            case R.id.replayButton:
                handleReplay();
                break;
        }
    }

    private void handleReplay() {
        mainActivity.switchFragmentsAddToBackStack(R.id.fragment_container, GamePlayFragment.newInstance(new GamePlayFragmentData()));
        Sound.playReplaySfx();
    }

    private void handleHome() {
        Sound.playGamePlayMusic();
        HomePageFragement homePageFragement = new HomePageFragement();
        mainActivity.switchFragments(R.id.fragment_container, homePageFragement);
    }

    @Override
    public void onPause() {
        super.onPause();
        Sound.stopGameOverMusic();
    }

    @Override
    public void onResume() {
        super.onResume();
        Sound.playGameOverMusic();
    }
}
