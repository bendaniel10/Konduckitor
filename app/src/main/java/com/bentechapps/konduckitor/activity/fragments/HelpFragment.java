package com.bentechapps.konduckitor.activity.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment implements View.OnClickListener {


    private MainActivity mainActivity;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mainActivity = (MainActivity) getActivity();
        getView().findViewById(R.id.play_tutorial).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_tutorial:
                handleTutorial();
                break;
        }

    }

    private void handleTutorial() {
        GamePlayFragment gamePlayFragment =  new GamePlayTutorialFragment();
        GamePlayFragmentData gamePlayFragmentData = new GamePlayFragmentData(getActivity());
        gamePlayFragment.setGamePlayFragmentData(gamePlayFragmentData);

        mainActivity.switchFragmentsAddToBackStack(R.id.fragment_container, gamePlayFragment);
    }
}
