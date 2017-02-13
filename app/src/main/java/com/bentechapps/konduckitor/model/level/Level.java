package com.bentechapps.konduckitor.model.level;

import android.content.Context;

import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.MissionFactory;
import com.bentechapps.konduckitor.model.mission.MissionInfoHolder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel on 5/2/2015.
 */
public abstract class Level implements Serializable {

    private MissionInfoHolder missionInfoHolder;

    public Level() {
        missionInfoHolder = new MissionInfoHolder();
    }

    public static Level getCurrentLevel(Context context) {
        return LevelFactory.listLevels().get(ApplicationData.getInstance().getCurrentLevel() - 1);
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract int getLevelNumber();

    public abstract int getDrawable();

    public MissionInfoHolder getMissionInfoHolder() {
        return missionInfoHolder;
    }

    public void setMissionInfoHolder(MissionInfoHolder missionInfoHolder) {
        this.missionInfoHolder = missionInfoHolder;
    }

    public List<Mission> listMission() {
        return MissionFactory.listMissions(this);
    }
}
