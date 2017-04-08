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
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.view.animation.AnimationFactory;

/**
 * Created by Daniel on 5/13/2015.
 */
public class MissionItem extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = MissionItem.class.getSimpleName();
    private final ImageView lockedImage;
    private final TextView missionNumber;
    private final ApplicationData appData;
    private final ImageView passImage;

    private Mission mission;
    private Level level;
    private boolean isLocked;

    public MissionItem(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mission_item, this, true);

        lockedImage = (ImageView) findViewById(R.id.locked_image);
        missionNumber = (TextView) findViewById(R.id.mission_number);
        appData = ApplicationData.getInstance();
        passImage = (ImageView) findViewById(R.id.pass_image);
        setOnClickListener(this);
    }

    public MissionItem setMission(Mission mission) {
        this.mission = mission;
        this.isLocked = isLocked();

        boolean isLocked = isLocked();

        lockedImage.setVisibility(isLocked ? VISIBLE : GONE);
        missionNumber.setText(String.valueOf(mission.getMission()));
        missionNumber.setVisibility(!isLocked ? VISIBLE : GONE);
        passImage.setVisibility((level.getLevelNumber() < appData.getCurrentLevel()) || mission.getMission() < appData.getCurrentMission() && level.getLevelNumber() <= appData.getCurrentLevel() ? VISIBLE : GONE);
        return this;
    }

    private boolean isLocked() {
        boolean locked;

        //first level, first mission always unlocked.
        if (mission.getMission() == 1 && level.getLevelNumber() == 1) {
            return false;
        }

        int cachedLevel = ApplicationData.getInstance().getCurrentLevel();
        int cachedMission = ApplicationData.getInstance().getCurrentMission();

        //unlock all previous missions in passed levels.
        if(level.getLevelNumber() < cachedLevel) {
            return false;
        }

        //
        if(level.getLevelNumber() <= cachedLevel && mission.getMission() <= cachedMission) {
            locked = false;
        } else {
            locked = true;
        }



        return locked;
    }

    @Override
    public void onClick(View v) {

        if (this.isLocked) {
            lockedImage.startAnimation(AnimationFactory.newWobbleAnimation());
        } else {
            MainActivity mainActivity = ((MainActivity) getContext());

            GamePlayFragmentData gamePlayFragmentData = new GamePlayFragmentData();
            gamePlayFragmentData.setIsMissionMode(true);

            mission.restartMission();
            gamePlayFragmentData.setCurrentMission(mission);

            gamePlayFragmentData.setCurrentLevel(level);

            level.getMissionInfoHolder().resetAllExceptScore();
            level.getMissionInfoHolder().setScore(0);//above doesn't resetAllExceptScore score see doc.

            gamePlayFragmentData.setMissionInfoHolder(level.getMissionInfoHolder());

            mainActivity.switchFragmentsAddToBackStack(R.id.fragment_container, GamePlayFragment.newInstance(gamePlayFragmentData));
        }
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
