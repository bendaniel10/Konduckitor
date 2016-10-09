package com.bentechapps.konduckitor.view.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.view.animation.AnimationFactory;

/**
 * Created by Daniel on 5/13/2015.
 */
public class MissionItem extends RelativeLayout implements View.OnClickListener {
    private final ImageView lockedImage;
    private final TextView missionNumber;
    private final ApplicationData appData;
    private final ImageView passImage;

    private Mission mission;
    private Level level;

    public MissionItem(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mission_item, this, true);

        lockedImage = (ImageView) findViewById(R.id.locked_image);
        missionNumber = (TextView) findViewById(R.id.mission_number);
        appData = ApplicationData.getInstance(context);
        passImage = (ImageView) findViewById(R.id.pass_image);
        setOnClickListener(this);
    }

    public MissionItem setMission(Mission mission) {
        this.mission = mission;
        boolean isLocked = mission.getMission() > appData.getCurrentMission();
        lockedImage.setVisibility(isLocked ? VISIBLE : GONE);
        missionNumber.setText(String.valueOf(mission.getMission()));
        missionNumber.setVisibility(!isLocked ? VISIBLE : GONE);
        passImage.setVisibility(mission.getMission() < appData.getCurrentMission() ? VISIBLE : GONE);
        return this;
    }

    @Override
    public void onClick(View v) {

        if (ApplicationData.getInstance(getContext()).getCurrentMission() < mission.getMission()) {
            lockedImage.startAnimation(AnimationFactory.newWobbleAnimation());
        } else {
            MainActivity mainActivity = ((MainActivity) getContext());
            GamePlayFragment gamePlayFragment = mission.getGamePlayFragment();
            gamePlayFragment.getGamePlayFragmentData().setIsMissionMode(true);
            gamePlayFragment.getGamePlayFragmentData().setCurrentMission(mission);
            gamePlayFragment.getGamePlayFragmentData().setCurrentLevel(level);
            mainActivity.setGamePlayFragment(gamePlayFragment);

            mainActivity.switchFragmentsAddToBackStack(gamePlayFragment);
        }
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}
