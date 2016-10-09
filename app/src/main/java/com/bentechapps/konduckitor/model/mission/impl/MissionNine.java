package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.BeatHighScoreSubMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainAngryPassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthAtThresholdMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TimedPowerUpUseMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionNine extends Mission {
    public MissionNine(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new BeatHighScoreSubMission(missionInfoHolder, level, this));
        addSubMission(new MaintainHealthAtThresholdMission(missionInfoHolder, level, this).setFactor(30));
        addSubMission(new MaintainAngryPassengersMission(missionInfoHolder, level, this).setFactor(3));
    }

    @Override
    public int getMission() {
        return 9;
    }
}
