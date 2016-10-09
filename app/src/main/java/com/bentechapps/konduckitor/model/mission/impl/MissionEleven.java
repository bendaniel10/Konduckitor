package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.AllowPassengerEscapeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.BeatHighScoreSubMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.UsePowerUpsMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionEleven extends Mission {
    public MissionEleven(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new UsePowerUpsMission(missionInfoHolder, level, this).setFactor(8));
        addSubMission(new AllowPassengerEscapeMission(missionInfoHolder, level, this).setFactor(5));
        addSubMission(new BeatHighScoreSubMission(missionInfoHolder, level, this));
    }

    @Override
    public int getMission() {
        return 11;
    }
}
