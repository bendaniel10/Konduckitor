package com.bentechapps.konduckitor.view.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.model.mission.Mission;
import com.bentechapps.konduckitor.view.adapter.StartMissionDialogAdapter;

/**
 * Created by Daniel on 5/14/2015.
 */
public class StartMissionDialog extends Dialog implements View.OnClickListener{
    private final Mission mission;
    private final Context context;
    private ImageButton okButton;
    private TextView missionTitle;
    private ListView missionList;

    public StartMissionDialog(Context context, Mission mission) {
        super(context);
        this.context = context;
        this.mission = mission;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_button:
                this.dismiss();
                ((MainActivity) context).getGamePlayFragment().getGamePlayHeaderView().handlePause();
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start_mission_dialog);
        okButton = (ImageButton) findViewById(R.id.ok_button);
        missionTitle = (TextView) findViewById(R.id.mission_title);
        missionList = (ListView) findViewById(R.id.mission_list);

        setCanceledOnTouchOutside(false);
        setCancelable(false);

        missionTitle.setText(String.format("Mission %s", mission.getMission()));
        missionList.setAdapter(new StartMissionDialogAdapter(mission, getContext()));
        okButton.setOnClickListener(this);
    }
}
