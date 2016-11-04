package com.bentechapps.konduckitor.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bentech.android.appcommons.activity.AppCommonsActivity;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.activity.fragments.HomePageFragement;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.sound.Sound;


public class MainActivity extends AppCommonsActivity {


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sound.initializeGamePlaySounds(this);
        switchFragments(R.id.fragment_container, new HomePageFragement());

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        hideToolBar();
    }

    @Override
    public void onBackPressed() {
        final Fragment fragmentContainer = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragmentContainer instanceof GamePlayFragment) {
            perfomGameExitConfirmation(fragmentContainer);
        } else {
            super.onBackPressed();
        }
    }

    private void perfomGameExitConfirmation(final Fragment fragmentContainer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.progress_will_not_be_saved_continue_exit);
        builder.setTitle(R.string.confirm_exit);
        builder.setPositiveButton(R.string.go_home, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (fragmentContainer instanceof GamePlayTutorialFragment) {
                    if (((GamePlayTutorialFragment) fragmentContainer).getShowcaseView().isShown()) {
                        ((GamePlayTutorialFragment) fragmentContainer).getShowcaseView().hide();
                    }
                }
                MainActivity.super.onBackPressed();
                ((GamePlayFragment) fragmentContainer).getLoop().cancel(true);
                ((GamePlayFragment) fragmentContainer).setLoop(null);

            }
        });
        builder.setNegativeButton(R.string.stay_in_game, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((GamePlayFragment) fragmentContainer).getGamePlayHeaderView().getGamePlayHeaderData().setPaused(true);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Main", "Goodbye Conductor");
        Sound.releaseMediaPlayers();
        Sound.releaseSoundPool();
    }
}
