package com.bentechapps.konduckitor.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;
import com.bentechapps.konduckitor.view.custom.StartMissionListItem;

import java.util.List;

/**
 * Created by Daniel on 5/14/2015.
 */
public class StartMissionDialogAdapter extends BaseAdapter {

    private final Context context;
    private Mission mission;
    private List<SubMission> subMissionList;
    private boolean isShowFullDetail;

    public StartMissionDialogAdapter(Mission mission, Context context) {
        this.mission = mission;
        this.context = context;
        initStartMissionList();
    }

    public StartMissionDialogAdapter setIsShowFullDetail(boolean isShowFullDetail) {
        this.isShowFullDetail = isShowFullDetail;
        return this;
    }

    private void initStartMissionList() {
        subMissionList = mission.listMissions();
    }

    @Override
    public int getCount() {
        return subMissionList.size();
    }

    @Override
    public Object getItem(int position) {
        return subMissionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StartMissionListItem  startMissionListItem = new StartMissionListItem(context).setIsShowFullDetails(isShowFullDetail);
        startMissionListItem.setSubMission((SubMission) getItem(position));
        convertView  = startMissionListItem;
        return convertView;
    }
}
