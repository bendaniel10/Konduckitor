package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TargetReceivedMoneyMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TimedFinishOthersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.UsePowerUpsMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionSixteen extends Mission {
    public MissionSixteen(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new UsePowerUpsMission(missionInfoHolder, level, this).setFactor(10));
        addSubMission(new TargetReceivedMoneyMission(missionInfoHolder, level, this).setFactor(5000));
        addSubMission(new TimedFinishOthersMission(missionInfoHolder, level, this).setFactor(600));
    }

    @Override
    public int getMission() {
        return 16;
    }
}
