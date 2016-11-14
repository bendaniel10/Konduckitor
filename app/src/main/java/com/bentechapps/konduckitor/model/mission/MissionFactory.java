package com.bentechapps.konduckitor.model.mission;

import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bendaniel on 13/11/2016.
 */

public class MissionFactory {

    private static MissionBuilder missionBuilder;
    private static List<Mission> missions;

    private synchronized static MissionBuilder builder(Level level) {
        if (missionBuilder == null) {
            missionBuilder = new MissionBuilder(level);
        }

        return missionBuilder;
    }

    private static void recycleBuilder(Level level) {
        missionBuilder = new MissionBuilder(level);
    }

    public static List<Mission> listMissions(Level level) {

        if (missions != null) {
            return missions;
        }

        missions = new ArrayList<>();

        MissionBuilder missionBuilder = builder(level);

        missionBuilder.withMissionNumber(1)
                .withSubMissions(Arrays.asList(
                        new
                ));
    }


    private static class MissionBuilder implements Serializable {
        private final Level level;
        private List<SubMission> subMissionList;
        private int missionNumber;

        public MissionBuilder(Level level) {
            this.level = level;
            subMissionList = new ArrayList<>();
        }

        MissionBuilder withSubMissions(List<SubMission> subMissions) {
            subMissionList.clear();
            subMissionList.addAll(subMissions);

            return this;
        }

        MissionBuilder withMissionNumber(int missionNumber) {
            this.missionNumber = missionNumber;
            return this;
        }

        Mission build() {
            final Mission mission = new Mission(level) {
                @Override
                public int getMission() {
                    return missionNumber;
                }
            };
            mission.addSubMissions(MissionBuilder.this.subMissionList);
            return mission;
        }
    }
}
