package com.bentechapps.konduckitor.activity.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.util.SystemUiHider;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.data.GamePlayHeaderData;
import com.bentechapps.konduckitor.data.GamePlayTailData;
import com.bentechapps.konduckitor.model.denomination.impl.DefaultConductorWalletDenomination;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.GamePlayHeaderView;
import com.bentechapps.konduckitor.view.GamePlayPersonTile;
import com.bentechapps.konduckitor.view.GamePlayTailView;
import com.bentechapps.konduckitor.view.adapter.PersonTileAdapter;
import com.bentechapps.konduckitor.view.custom.PauseGameDialog;
import com.bentechapps.konduckitor.view.custom.StartMissionDialog;

import org.w3c.dom.Text;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class GamePlayFragment extends Fragment {
    protected GamePlayHeaderView gamePlayHeaderView;
    protected GameLooper loop;
    protected PersonTileAdapter personTileAdapter;
    protected GamePlayFragmentData gamePlayFragmentData;
    protected ApplicationData appData;
    protected GamePlayTailView gamePlayTailView;
    protected GridView gridview;
    public static final int TARGET_FPS = 10;
    public static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    public GamePlayFragmentData getGamePlayFragmentData() {
        return gamePlayFragmentData;
    }

    public GamePlayFragment setGamePlayFragmentData(GamePlayFragmentData gamePlayFragmentData) {
        this.gamePlayFragmentData = gamePlayFragmentData;
        return this;
    }


    public GameLooper getLoop() {
        if (loop == null)
            loop = new GameLooper();
        return loop;
    }

    public void setLoop(GameLooper loop) {
        this.loop = loop;
    }


    public GamePlayTailView getGamePlayTailView() {
        return gamePlayTailView;
    }

    public void setGamePlayTailView(GamePlayTailView gamePlayTailView) {
        this.gamePlayTailView = gamePlayTailView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_game_play, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeGame();
    }

    public void initializeGame() {
        appData = ApplicationData.getInstance(getActivity());
        initUI();
        init();
    }

    protected void initUI() {
        gridview = (GridView) getView().findViewById(R.id.gridview);
        gridview.setAdapter(personTileAdapter = new PersonTileAdapter(getActivity()));
        gamePlayHeaderView = (GamePlayHeaderView) getView().findViewById(R.id.game_play_header_view);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((GamePlayPersonTile) gridview.getAdapter().getItem(position)).onClick(view);
            }
        });
        gamePlayTailView = (GamePlayTailView) getView().findViewById(R.id.game_play_tail_view);
        if (gamePlayFragmentData.isMissionMode()) {
            StartMissionDialog startMissionDialog = new StartMissionDialog(getActivity(), getGamePlayFragmentData().getCurrentMission());
            startMissionDialog.show();
        } else {
            PauseGameDialog pauseGameDialog = new PauseGameDialog(getActivity(), null);
            pauseGameDialog.show();
        }
    }

    public static void handleRestartAndNextMissionInit(GamePlayFragment gamePlayFragment) {
        GamePlayFragmentData gamePlayFragmentData = new GamePlayFragmentData(gamePlayFragment.getActivity());
        if (gamePlayFragment.getGamePlayFragmentData().isMissionMode()) {
            gamePlayFragmentData.setCurrentLevel(gamePlayFragment.getGamePlayFragmentData().getCurrentLevel());
            gamePlayFragmentData.setCurrentMission(gamePlayFragment.getGamePlayFragmentData().getCurrentMission());
            gamePlayFragmentData.setIsMissionMode(true);
        }
        gamePlayFragment.setGamePlayFragmentData(gamePlayFragmentData);
        if (gamePlayFragmentData.isMissionMode()) {
            gamePlayFragmentData.setCurrentMission(gamePlayFragment.getGamePlayFragmentData().getCurrentMission().restartMission());
        }
    }

    protected void init() {
        GamePlayTailData gamePlayTailData = GamePlayTailData.newGamePlayTailData(new DefaultConductorWalletDenomination(getActivity()), 0, ShopItem.list(getActivity()).get(appData.getDefaultPowerUp()));
        gamePlayTailView.setGamePlayTailData(gamePlayTailData);
        GamePlayHeaderData gamePlayHeaderData = GamePlayHeaderData.newGamePlayHeaderData(true, 0, GamePlayHeaderData.MAX_LIFE, 0);
        gamePlayHeaderView.setGamePlayHeaderData(gamePlayHeaderData);
        loop = getLoop();
    }

    @Override
    public void onPause() {
        super.onPause();
        Sound.stopGamePlayMusic();
        Sound.stopDrivingByMusic();
        getLoop().cancel(true);
        loop = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoop().execute();
        Sound.playGamePlayMusic();
        Sound.playDrivingByMusic();
        gamePlayTailView.doWalletScroll();
    }

    public GamePlayHeaderView getGamePlayHeaderView() {
        return gamePlayHeaderView;
    }

    public void setGamePlayHeaderView(GamePlayHeaderView gamePlayHeaderView) {
        this.gamePlayHeaderView = gamePlayHeaderView;
    }

    public PersonTileAdapter getPersonTileAdapter() {
        return personTileAdapter;
    }

    public class GameLooper extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            long lastLoopTime = System.nanoTime();

            while (true) {

                if (this.isCancelled()) {
                    Log.i("LOOPER", "Looping Thread cancelled.");
                    break;
                } else {
                    if (!gamePlayHeaderView.getGamePlayHeaderData().isPaused()) {
                        // work out how long its been since the last update, this
                        // will be used to calculate how far the entities should
                        // move this loop
                        long now = System.nanoTime();
                        long updateLength = now - lastLoopTime;
                        lastLoopTime = now;
                        double delta = updateLength / ((double) OPTIMAL_TIME);

                        //power up processing.
                        getGamePlayTailView().getGamePlayTailData().getDefaultShopItem().doGameUpdates(GamePlayFragment.this, delta);
                        getGamePlayTailView().getGamePlayTailData().getDefaultShopItem().doGameRender(GamePlayFragment.this);

                        //Header Tile Processing
                        gamePlayHeaderView.doGameUpdates(GamePlayFragment.this, delta);
                        gamePlayHeaderView.doGameRender(GamePlayFragment.this);


                        //mission processing
                        if (getGamePlayFragmentData().isMissionMode()) {
                            getGamePlayFragmentData().getCurrentMission().doGameUpdates(GamePlayFragment.this, delta);
                            getGamePlayFragmentData().getCurrentMission().doGameRender(GamePlayFragment.this);
                        }

                        //Individual Tile Processing
                        GridView gridView = GamePlayFragment.this.getGridView();
                        for (int j = gridView.getAdapter().getCount() - 1; j >= 0; j--) {
                            GamePlayPersonTile tile = (GamePlayPersonTile) gridView.getAdapter().getItem(j);
                            tile.doGameUpdates(GamePlayFragment.this, delta);
                            tile.doGameRender(GamePlayFragment.this);
                        }

                        try {
                            //OPTIMAL_TIME + lastLoopTime - System.nanoTime
                            Thread.sleep(Math.max(0, (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

            return null;
        }

    }

    public void showGameplayToast(String message, int drawableId) {
        LayoutInflater inflater = getLayoutInflater(getArguments());

        View customToastroot = inflater.inflate(R.layout.gameplay_toast, null);
        ((ImageView) customToastroot.findViewById(R.id.image)).setImageDrawable(getResources().getDrawable(drawableId));
        ((TextView) customToastroot.findViewById(R.id.text)).setText(message);

        Toast customtoast = new Toast(getActivity());

        customtoast.setView(customToastroot);
        customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 0);
        customtoast.setDuration(Toast.LENGTH_LONG);
        customtoast.show();
    }

    public GridView getGridView() {
        return gridview;
    }
}
