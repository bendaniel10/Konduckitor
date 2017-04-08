package com.bentechapps.konduckitor.model.mission;

import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.AllowPassengerEscapeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.BeatHighScoreSubMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.DoNotUseDenominationMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.ExactAmountMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.GiveChangeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainAngryPassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthAtThresholdMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.MaintainHealthForTimeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.OverPayPassengersSubMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.PlayTimeMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettleFemalesMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettleMalesMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.SettlePassengersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TargetReceivedMoneyMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TargetScoreMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TimedFinishOthersMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.TimedPowerUpUseMission;
import com.bentechapps.konduckitor.model.mission.impl.submission.impl.UsePowerUpsMission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bendaniel on 13/11/2016.
 */

public class MissionFactory {

    private static MissionBuilder missionBuilder;

    private synchronized static MissionBuilder builder(Level level) {
        if (missionBuilder == null) {
            missionBuilder = new MissionBuilder(level);
            return missionBuilder;
        }

        return missionBuilder;
    }

    private static void recycleBuilder(Level level) {
        missionBuilder = new MissionBuilder(level);
    }

    public static List<Mission> listMissions(Level level) {

        List<Mission> missions = new ArrayList<>(Constants.MAX_LEVEL);

        builder(level);

        int missionNumber = 1;

        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new SettlePassengersMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new TargetReceivedMoneyMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new TargetScoreMission(level.getMissionInfoHolder(), level.getLevelNumber())
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new UsePowerUpsMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new GiveChangeMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new MaintainHealthAtThresholdMission(level.getMissionInfoHolder(), level.getLevelNumber())
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new PlayTimeMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new SettleFemalesMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new MaintainAngryPassengersMission(level.getMissionInfoHolder(), level.getLevelNumber())
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new AllowPassengerEscapeMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new ExactAmountMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new MaintainHealthForTimeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new TimedPowerUpUseMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new SettleMalesMission(level.getMissionInfoHolder(), level.getLevelNumber()),
                        new UsePowerUpsMission(level.getMissionInfoHolder(), level.getLevelNumber())
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new AllowPassengerEscapeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(4),
                        new DoNotUseDenominationMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(10),
                        new PlayTimeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .
                                        setCompletedFactor(400)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new UsePowerUpsMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(6),
                        new ExactAmountMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(15),
                        new TimedFinishOthersMission(level.getMissionInfoHolder(), level.getLevelNumber())
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new MaintainHealthForTimeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(30),
                        new GiveChangeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(1000),
                        new TargetScoreMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(1000)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new OverPayPassengersSubMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(4),
                        new MaintainHealthAtThresholdMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(30),
                        new MaintainAngryPassengersMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(3)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new SettleFemalesMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(16),
                        new GiveChangeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(1400),
                        new DoNotUseDenominationMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(20)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new UsePowerUpsMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(8),
                        new AllowPassengerEscapeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(5),
                        new OverPayPassengersSubMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(6)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new SettlePassengersMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(30),
                        new TargetReceivedMoneyMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(4000),
                        new TargetScoreMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(2000)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new MaintainHealthForTimeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(40),
                        new SettleMalesMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(16),
                        new TimedFinishOthersMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(500)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new MaintainHealthAtThresholdMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(35),
                        new MaintainAngryPassengersMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(4),
                        new GiveChangeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(2000)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new ExactAmountMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(20),
                        new DoNotUseDenominationMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(50),
                        new BeatHighScoreSubMission(level.getMissionInfoHolder(), level.getLevelNumber())
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new UsePowerUpsMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(10),
                        new TargetReceivedMoneyMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(5000),
                        new TimedFinishOthersMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(600)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new ExactAmountMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(25),
                        new AllowPassengerEscapeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(5),
                        new GiveChangeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(2200)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new SettleMalesMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(18),
                        new PlayTimeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(500),
                        new MaintainHealthAtThresholdMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(40)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new GiveChangeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(2500),
                        new DoNotUseDenominationMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(10),
                        new AllowPassengerEscapeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(6)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new TimedPowerUpUseMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(60),
                        new TargetScoreMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(3500),
                        new SettlePassengersMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(40)
                ));
        missions.add(missionBuilder.build());

        recycleBuilder(level);
        missionBuilder.withMissionNumber(missionNumber++)
                .withSubMissions(Arrays.asList(
                        new SettleFemalesMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(18),
                        new MaintainHealthForTimeMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(45),
                        new MaintainAngryPassengersMission(level.getMissionInfoHolder(), level.getLevelNumber())
                                .setCompletedFactor(4)
                ));
        missions.add(missionBuilder.build());

        return missions;
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

            for (SubMission subMission : MissionBuilder.this.subMissionList) {
                subMission.setParentMission(mission);
            }

            mission.addSubMissions(MissionBuilder.this.subMissionList);
            return mission;
        }
    }
}
