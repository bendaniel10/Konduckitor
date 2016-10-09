package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettlePassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TargetScoreMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TargetReceivedMoneyMission;

/**
 * Created by Daniel on 4/10/2015.
 */
public class MissionOne extends Mission {
    public MissionOne(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new SettlePassengersMission(missionInfoHolder, level, this).setFactor(20));
        addSubMission(new TargetReceivedMoneyMission(missionInfoHolder, level, this).setFactor(2000));
        addSubMission(new TargetScoreMission(missionInfoHolder, level, this).setFactor(500));
    }

    @Override
    public int getMission() {
        return 1;
    }
}
