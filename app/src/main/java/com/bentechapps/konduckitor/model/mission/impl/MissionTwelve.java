package com.bentechapps.konduckitor.model.mission.impl;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettlePassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TargetReceivedMoneyMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TargetScoreMission;

/**
 * Created by Daniel on 4/30/2015.
 */
public class MissionTwelve extends Mission  {
    public MissionTwelve(GamePlayFragment gamePlayFragment, int level) {
        super(gamePlayFragment, level);
        addSubMission(new SettlePassengersMission(missionInfoHolder, level, this).setFactor(30));
        addSubMission(new TargetReceivedMoneyMission(missionInfoHolder, level, this).setFactor(4000));
        addSubMission(new TargetScoreMission(missionInfoHolder, level, this).setFactor(2000));
    }

    @Override
    public int getMission() {
        return 12;
    }
}
