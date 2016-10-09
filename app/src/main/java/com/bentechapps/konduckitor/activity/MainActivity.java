package com.bentechapps.konduckitor.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.GameOverFragment;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.activity.fragments.HomePageFragement;
import com.bentechapps.konduckitor.activity.fragments.tutorial.GamePlayTutorialFragment;
import com.bentechapps.konduckitor.sound.Sound;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 */
public class MainActivity extends FragmentActivity {

    private GamePlayFragment gamePlayFragment;
    private GamePlayTutorialFragment gamePlayTutorialFragment;
    private HomePageFragement homePageFragementFragment;
    private GameOverFragment gameOverFragment;

    public GameOverFragment getGameOverFragment() {
        return gameOverFragment;
    }

    public void setGameOverFragment(GameOverFragment gameOverFragment) {
        this.gameOverFragment = gameOverFragment;
    }


    public HomePageFragement getHomePageFragementFragment() {
        return homePageFragementFragment;
    }

    public void setHomePageFragementFragment(HomePageFragement homePageFragementFragment) {
        this.homePageFragementFragment = homePageFragementFragment;
    }


    public GamePlayFragment getGamePlayFragment() {
        return gamePlayFragment;
    }

    public void setGamePlayFragment(GamePlayFragment gamePlayFragment) {
        this.gamePlayFragment = gamePlayFragment;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Sound.initializeGamePlaySounds(this);
        homePageFragementFragment = new HomePageFragement();
        switchFragments(homePageFragementFragment);
    }

    public void switchFragments(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    public void switchFragmentsAddToBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (gamePlayFragment != null && gamePlayFragment.isVisible()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Progress will not be saved, continue?");
            builder.setTitle("Confirm exit");
            builder.setPositiveButton("Continue to home", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (gamePlayFragment instanceof GamePlayTutorialFragment) {
                        if (((GamePlayTutorialFragment) gamePlayFragment).getShowcaseView().isShown()) {
                            ((GamePlayTutorialFragment) gamePlayFragment).getShowcaseView().hide();
                        }
                    }
                    MainActivity.super.onBackPressed();
                    gamePlayFragment.getLoop().cancel(true);
                    gamePlayFragment.setLoop(null);

                }
            });
            builder.setNegativeButton("Stay in game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().setPaused(true);
                    dialog.dismiss();
                }
            });
            builder.show();

        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Main", "Goodbye Conductor");
        Sound.releaseMediaPlayers();
        Sound.releaseSoundPool();
    }
}
