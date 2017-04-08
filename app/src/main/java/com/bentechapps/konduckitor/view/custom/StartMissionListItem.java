package com.bentechapps.konduckitor.view.custom;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentech.android.appcommons.utils.DrawableUtils;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.mission.impl.submission.SubMission;

/**
 * Created by Daniel on 5/14/2015.
 */
public class StartMissionListItem extends RelativeLayout {

    private final ImageView subMissionImage;
    private final TextView subMissionDesc;
    private SubMission subMission;
    private boolean isShowFullDetails;

    public StartMissionListItem(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.start_mission_list_item, this, true);
        subMissionImage = (ImageView) findViewById(R.id.sub_mission_image);
        subMissionDesc = (TextView) findViewById(R.id.sub_mission_desc);

    }

    public StartMissionListItem setIsShowFullDetails(boolean isShowFullDetails) {
        this.isShowFullDetails = isShowFullDetails;
        return this;
    }

    public StartMissionListItem setSubMission(SubMission subMission) {
        this.subMission = subMission;
        subMissionImage.setImageDrawable(DrawableUtils.getDrawable(getContext(), subMission.getSubMissionDrawable()));
        if(isShowFullDetails) {
            subMissionDesc.setText(subMission.getSubMissionDescription());
            if(subMission.isSubMissionCompleted()) {
                DrawableUtils.setBackgroundDrawable(subMissionDesc, new ColorDrawable(ContextCompat.getColor(getContext(), R.color.light_green)));
            } else {
                DrawableUtils.setBackgroundDrawable(subMissionDesc, new ColorDrawable(ContextCompat.getColor(getContext(), R.color.light_red)));
            }
        } else {
            subMissionDesc.setText(subMission.getSubMissionDescription().split(",")[0]);
        }
        return this;
    }
}
