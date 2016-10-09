package com.bentechapps.konduckitor.view.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        subMissionImage.setImageDrawable(getResources().getDrawable(subMission.getSubMissionDrawable()));
        if(isShowFullDetails) {
            subMissionDesc.setText(subMission.getSubMissionDescription());
            if(subMission.isSubMissionCompleted()) {
                if (android.os.Build.VERSION.SDK_INT >= 16) {
                    subMissionDesc.setBackground(getResources().getDrawable(R.color.light_green));
                } else {
                    subMissionDesc.setBackgroundDrawable(getResources().getDrawable(R.color.light_green));
                }
            } else {
                if (android.os.Build.VERSION.SDK_INT >= 16) {
                    subMissionDesc.setBackground(getResources().getDrawable(R.color.light_red));
                } else {
                    subMissionDesc.setBackgroundDrawable(getResources().getDrawable(R.color.light_red));
                }
            }
        } else {
            subMissionDesc.setText(subMission.getSubMissionDescription().split(",")[0]);
        }
        return this;
    }
}
