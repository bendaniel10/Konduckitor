package com.bentechapps.konduckitor.model.mission;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.engine.GameLoopItem;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.GamePlayHeaderView;
import com.bentechapps.konduckitor.view.custom.MissionCompletedDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 4/10/2015.
 */
public abstract class Mission implements GameLoopItem {

    private final Level level;
    private List<SubMission> subMissionList = new ArrayList<>();


    public Mission(Level level) {
        this.level = level;
    }

    /**
     * Not zero indexed based!
     *
     * @return
     */
    public static Mission getMission(GamePlayFragment gamePlayFragment) {
        ApplicationData applicationData = ApplicationData.getInstance(gamePlayFragment.getActivity());
        int missionNumber = applicationData.getCurrentMission();
        return Level.getLevel(gamePlayFragment).listMission(gamePlayFragment).get(missionNumber - 1);
    }

    public Mission restartMission() {
        for (SubMission subMission : subMissionList) {
            subMission.restartSubMission();
        }
        return this;
    }

    public abstract int getMission();

    public void addSubMission(SubMission subMission) {
        subMissionList.add(subMission);
    }

    void addSubMissions(List<SubMission> subMissions) {
        subMissionList.addAll(subMissions);
    }

    public List<SubMission> listMissions() {
        return subMissionList;
    }

    public boolean isMissionComplete() {
        boolean isComplete = true;
        for (final SubMission subMission : subMissionList) {
            if (!subMission.isSubMissionCompleted()) {
                if (subMission.isGameOverOnSubMissionFail()) {
                    gamePlayFragment.getGamePlayHeaderView().doGameOver();
                }
                isComplete = false;
            } else {
                if (!subMission.isUserNotified() && !subMission.isGameOverOnSubMissionFail()) {
                    subMission.setIsUserNotified(true);
                    gamePlayFragment.getGamePlayHeaderView().post(new Runnable() {
                        @Override
                        public void run() {
                            gamePlayFragment.showGameplayToast(subMission.getSubMissionDescription(), subMission.getSubMissionDrawable());
                        }
                    });
                }
            }
        }
        return isComplete;
    }

    @Override
    public void doGameUpdates(GamePlayFragment gamePlayFragment, double delta) {
        GamePlayFragmentData gamePlayFragmentData = gamePlayFragment.getGamePlayFragmentData();
        if (gamePlayFragmentData.isMissionMode()) {
            if (gamePlayFragmentData.getCurrentMission().isMissionComplete()) {
                handleSaveAndMoveToNextMission(gamePlayFragment);
            }
        }
    }

    @Override
    public void doGameRender(final GamePlayFragment gamePlayFragment) {
        GamePlayHeaderView gamePlayHeaderView = gamePlayFragment.getGamePlayHeaderView();
        GamePlayFragmentData gamePlayFragmentData = gamePlayFragment.getGamePlayFragmentData();

        if (gamePlayFragmentData.isMissionMode()) {
            if (gamePlayFragmentData.getCurrentMission().isMissionComplete()) {
                gamePlayHeaderView.post(new Runnable() {
                    @Override
                    public void run() {
                        MissionCompletedDialog missionCompletedDialog = new MissionCompletedDialog(gamePlayFragment.getActivity(), gamePlayFragment);
                        missionCompletedDialog.show();
                    }
                });
            }
        }

    }

    private void handleSaveAndMoveToNextMission(GamePlayFragment gamePlayFragment) {
        ApplicationData appData = ApplicationData.getInstance(gamePlayFragment.getActivity());
        GamePlayHeaderView gamePlayHeaderView = gamePlayFragment.getGamePlayHeaderView();
        //stop game
        gamePlayHeaderView.getGamePlayHeaderData().setPaused(!gamePlayHeaderView.getGamePlayHeaderData().isPaused());

        //save money.
        appData.incrementReputation((int) ((gamePlayHeaderView.getGamePlayHeaderData().getPlayTime() * gamePlayHeaderView.getGamePlayHeaderData().getPoints()) / 100));

        //save score
        if (appData.getHighScore() < gamePlayHeaderView.getGamePlayHeaderData().getPoints()) {
            appData.setHighScore(gamePlayHeaderView.getGamePlayHeaderData().getPoints());
            Sound.playApplauseSfx();
        }

        //save mission progress
        appData.incrementMissionProgress(gamePlayFragment);

    }
}
