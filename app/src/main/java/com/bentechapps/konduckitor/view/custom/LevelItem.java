package com.bentechapps.konduckitor.view.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.level.Level;

/**
 * Created by Daniel on 5/10/2015.
 */
@Deprecated
public class LevelItem extends RelativeLayout {

    private final ImageView levelImage;
    private final ImageView lockedImage;
    private final ImageView passImage;
    private final TextView levelName;
    private final TextView levelProgress;
    private final ApplicationData appData;
    Level level;

    public LevelItem(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.level_item, this, true);
        levelImage = (ImageView) findViewById(R.id.level_image);
        lockedImage = (ImageView) findViewById(R.id.locked_image);
        passImage = (ImageView) findViewById(R.id.pass_image);
        levelName = (TextView) findViewById(R.id.level_name);
        levelProgress = (TextView) findViewById(R.id.level_progress);
        appData = ApplicationData.getInstance();
    }

    public LevelItem setLevel(Level level) {
        this.level = level;
        this.levelImage.setImageDrawable(getResources().getDrawable(level.getDrawable()));
        this.levelName.setText(level.getName());
        passImage.setVisibility(level.getLevelNumber() < appData.getCurrentLevel() ? VISIBLE : GONE);
        boolean isLocked = level.getLevelNumber() > appData.getCurrentLevel();
        lockedImage.setVisibility(isLocked ? VISIBLE : GONE);
        int missionSize = level.listMission().size();
        if(level.getLevelNumber() < appData.getCurrentLevel()) {
            levelProgress.setText(isLocked
                    ? "[LOCKED]"
                    : String.format("%s / %s", missionSize, missionSize));
        } else {
            levelProgress.setText(isLocked
                    ? "[LOCKED]"
                    : String.format("%s / %s", appData.getCurrentMission(), missionSize));
        }

        return this;
    }

}
