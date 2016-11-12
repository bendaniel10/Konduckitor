package com.bentechapps.konduckitor.model.level;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.MissionEight;
import com.bentechapps.konduckitor.model.mission.impl.MissionEighteen;
import com.bentechapps.konduckitor.model.mission.impl.MissionEleven;
import com.bentechapps.konduckitor.model.mission.impl.MissionFifteen;
import com.bentechapps.konduckitor.model.mission.impl.MissionFive;
import com.bentechapps.konduckitor.model.mission.impl.MissionFour;
import com.bentechapps.konduckitor.model.mission.impl.MissionFourteen;
import com.bentechapps.konduckitor.model.mission.impl.MissionNine;
import com.bentechapps.konduckitor.model.mission.impl.MissionNineteen;
import com.bentechapps.konduckitor.model.mission.impl.MissionOne;
import com.bentechapps.konduckitor.model.mission.impl.MissionSeven;
import com.bentechapps.konduckitor.model.mission.impl.MissionSeventeen;
import com.bentechapps.konduckitor.model.mission.impl.MissionSix;
import com.bentechapps.konduckitor.model.mission.impl.MissionSixteen;
import com.bentechapps.konduckitor.model.mission.impl.MissionTen;
import com.bentechapps.konduckitor.model.mission.impl.MissionThirteen;
import com.bentechapps.konduckitor.model.mission.impl.MissionThree;
import com.bentechapps.konduckitor.model.mission.impl.MissionTwelve;
import com.bentechapps.konduckitor.model.mission.impl.MissionTwenty;
import com.bentechapps.konduckitor.model.mission.impl.MissionTwentyOne;
import com.bentechapps.konduckitor.model.mission.impl.MissionTwo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel on 5/2/2015.
 */
public abstract class Level implements Serializable {

    public static Level getLevel(GamePlayFragment gamePlayFragment) {
        return LevelFactory.listLevels().get(ApplicationData.getInstance(gamePlayFragment.getActivity()).getCurrentLevel() - 1);
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract int getLevel();

    public abstract int getDrawable();

    public List<Mission> listMission(GamePlayFragment gamePlayFragment) {
        return Arrays.asList(new MissionOne(gamePlayFragment, getLevel()), new MissionTwo(gamePlayFragment, getLevel()),
                new MissionThree(gamePlayFragment, getLevel()), new MissionFour(gamePlayFragment, getLevel()),
                new MissionFive(gamePlayFragment, getLevel()), new MissionSix(gamePlayFragment, getLevel()),
                new MissionSeven(gamePlayFragment, getLevel()), new MissionEight(gamePlayFragment, getLevel()),
                new MissionNine(gamePlayFragment, getLevel()), new MissionTen(gamePlayFragment, getLevel()),
                new MissionEleven(gamePlayFragment, getLevel()), new MissionTwelve(gamePlayFragment, getLevel()),
                new MissionThirteen(gamePlayFragment, getLevel()), new MissionFourteen(gamePlayFragment, getLevel()),
                new MissionFifteen(gamePlayFragment, getLevel()), new MissionSixteen(gamePlayFragment, getLevel()),
                new MissionSeventeen(gamePlayFragment, getLevel()), new MissionEighteen(gamePlayFragment, getLevel()),
                new MissionNineteen(gamePlayFragment, getLevel()), new MissionTwenty(gamePlayFragment, getLevel()),
                new MissionTwentyOne(gamePlayFragment, getLevel()));
    }
}
