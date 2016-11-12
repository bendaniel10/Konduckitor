package com.bentechapps.konduckitor.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.level.LevelFactory;
import com.bentechapps.konduckitor.view.custom.LevelItem;

import java.util.List;

/**
 * Created by Daniel on 5/10/2015.
 */
@Deprecated
public class ChooseLevelAdapter extends BaseAdapter {

    private Context context;
    List<Level> levelList;

    public ChooseLevelAdapter(Context context) {
        this.context = context;
        initLevelItems();
    }

    private void initLevelItems() {
        levelList = LevelFactory.listLevels();
    }

    @Override
    public int getCount() {
        return levelList.size();
    }

    @Override
    public Object getItem(int position) {
        return levelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LevelItem levelItem = new LevelItem(context);
        levelItem.setLevel((Level) getItem(position));
        convertView = levelItem;
        return convertView;
    }
}
