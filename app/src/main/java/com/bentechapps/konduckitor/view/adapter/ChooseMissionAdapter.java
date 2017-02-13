package com.bentechapps.konduckitor.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.view.custom.MissionItem;

import java.util.List;

/**
 * Created by Daniel on 5/13/2015.
 */
public class ChooseMissionAdapter extends BaseAdapter {

    private final Context context;
    private final Level level;
    private List<Mission> missionList;

    public ChooseMissionAdapter(Context context, Level level) {
        this.context = context;
        this.level = level;
        initMissionItems();
    }

    private void initMissionItems() {
        this.missionList = level.listMission();
    }

    @Override
    public int getCount() {
        return missionList.size();
    }

    @Override
    public Object getItem(int position) {
        return missionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MissionItem missionItem = new MissionItem(context);
        missionItem.setLevel(level);
        missionItem.setMission((Mission) getItem(position));
        convertView = missionItem;
        return convertView;
    }
}
