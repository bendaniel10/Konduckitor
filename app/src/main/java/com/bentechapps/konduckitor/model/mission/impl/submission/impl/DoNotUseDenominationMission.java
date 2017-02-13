package com.bentechapps.konduckitor.model.mission.impl.submission.impl;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 4/29/2015.
 */

//remember to keep track of returned money to wallet
public class DoNotUseDenominationMission extends SubMission {
    public DoNotUseDenominationMission(MissionInfoHolder missionInfoHolder, int level) {
        super(missionInfoHolder, level);
    }

    @Override
    public int getSubMissionDrawable() {
        return R.drawable.dont_use_denomination;
    }

    @Override
    public boolean isSubMissionCompleted() {
        return (isComplete = getCountOfFactorDenominationUsed() <= 0);
    }

    @Override
    public String getSubMissionDescription() {
        return String.format("Do not use %ss denomination, %s used", getCompletedFactor(), getCountOfFactorDenominationUsed());
    }

    @Override
    public boolean isGameOverOnSubMissionFail() {
        return true;
    }

    public int getCountOfFactorDenominationUsed() {
        int count = 0;
        switch (getCompletedFactor()) {
            case 10:
                count = missionInfoHolder.getTensUseCount();
                break;
            case 20:
                count = missionInfoHolder.getTwentiesUseCount();
                break;
            case 50:
                count = missionInfoHolder.getFiftiesUseCount();
                break;
        }
        return count;
    }
}
