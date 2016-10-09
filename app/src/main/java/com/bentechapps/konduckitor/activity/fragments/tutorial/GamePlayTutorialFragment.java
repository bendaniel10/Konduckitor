package com.bentechapps.konduckitor.activity.fragments.tutorial;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.Hint;
import com.bentechapps.konduckitor.activity.fragments.tutorial.hint.impl.PlayButtonHint;
import com.bentechapps.konduckitor.data.GamePlayHeaderData;
import com.bentechapps.konduckitor.data.GamePlayTailData;
import com.bentechapps.konduckitor.model.denomination.impl.DefaultConductorWalletDenomination;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.view.GamePlayPersonTile;
import com.bentechapps.konduckitor.view.tutorial.GamePlayTutorialHeaderView;
import com.bentechapps.konduckitor.view.tutorial.GamePlayTutorialTailView;
import com.bentechapps.konduckitor.view.tutorial.adapter.TutorialPersonTileAdapter;
import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamePlayTutorialFragment extends GamePlayFragment implements OnShowcaseEventListener {

    ShowcaseView showcaseView;
    private GridView gridview;
    private boolean isShowHint;
    private Hint currentHint;
    private ToolTip toolTip;
    private ToolTipRelativeLayout toolTipRelativeLayout;
    private ToolTipView toolTipView;
    private boolean isShowSubHint;


    public GamePlayTutorialFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_play_tutorial, container, false);
    }

    @Override
    public GamePlayFragment.GameLooper getLoop() {
        if (loop == null)
            loop = new GameLooper();
        return loop;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public boolean isShowSubHint() {
        return isShowSubHint;
    }

    public void setShowSubHint(boolean isShowSubHint) {
        this.isShowSubHint = isShowSubHint;
    }

    @Override
    protected void initUI() {
        gridview = (GridView) getView().findViewById(R.id.gridview);
        gridview.setAdapter(personTileAdapter = new TutorialPersonTileAdapter(getActivity()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((GamePlayPersonTile) gridview.getAdapter().getItem(position)).onClick(view);
            }
        });
        gamePlayHeaderView = (GamePlayTutorialHeaderView) getView().findViewById(R.id.game_play_header_view);
        gamePlayTailView = (GamePlayTutorialTailView) getView().findViewById(R.id.game_play_tail_view);
    }

    @Override
    protected void init() {
        GamePlayTailData gamePlayTailData = GamePlayTailData.newGamePlayTailData(new DefaultConductorWalletDenomination(getActivity()), 0, ShopItem.list(getActivity()).get(appData.getDefaultPowerUp()));
        gamePlayTailView.setGamePlayTailData(gamePlayTailData);
        GamePlayHeaderData gamePlayHeaderData = GamePlayHeaderData.newGamePlayHeaderData(true, 0, GamePlayHeaderData.MAX_LIFE, 0);
        gamePlayHeaderView.setGamePlayHeaderData(gamePlayHeaderData);
        loop = getLoop();
        showcaseView = new ShowcaseView.Builder(getActivity(), true)
                .setStyle(R.style.MyShowCaseTheme).build();
        showcaseView.setOnShowcaseEventListener(this);
        toolTipRelativeLayout = (ToolTipRelativeLayout) getView().findViewById(R.id.sub_hint);
        toolTip = new ToolTip()
                .withAnimationType(ToolTip.AnimationType.FROM_TOP)
                .withShadow()
                .withColor(getResources().getColor(R.color.saddle_brown))
                .withTextColor(Color.WHITE);
        setCurrentHint(new PlayButtonHint(this));
        setShowHint(true);

    }

    public boolean isShowHint() {
        return isShowHint;
    }

    public void setShowHint(boolean isShowHint) {
        this.isShowHint = isShowHint;
    }

    public Hint getCurrentHint() {
        return currentHint;
    }

    public void setCurrentHint(Hint currentHint) {
        this.currentHint = currentHint;
    }

    public void displayHint(final String message, final String title, final View anchor) {
        getView().post(new Runnable() {
            @Override
            public void run() {

                showcaseView.setTarget(new ViewTarget(anchor.getId(), getActivity()));
                showcaseView.setContentTitle(title);
                showcaseView.setContentText(message);
                showcaseView.show();

            }
        });
    }

    public void displayHint(final int messageResourceId, final int titleResourceId, final View anchor) {

        getView().post(new Runnable() {
            @Override
            public void run() {
                showcaseView.setTarget(new ViewTarget(anchor.getId(), getActivity()));
                showcaseView.setContentTitle(getResources().getString(titleResourceId));
                showcaseView.setContentText(getResources().getString(messageResourceId));
                showcaseView.show();
            }
        });
    }

    public void displaySubHint(final int messageResourceId, final View anchor) {
        dismissSubHint();
        toolTip.withText(getResources().getString(messageResourceId));
        getView().post(new Runnable() {
            @Override
            public void run() {
                toolTipView = toolTipRelativeLayout.showToolTipForView(toolTip, anchor);
            }
        });
    }

    public ToolTipView getSubHintView() {
        return toolTipView;
    }

    public void displaySubHint(String message, final View anchor) {
        dismissSubHint();
        toolTip.withText(message);
        getView().post(new Runnable() {
            @Override
            public void run() {
                toolTipView = toolTipRelativeLayout.showToolTipForView(toolTip, anchor);
            }
        });
    }

    public void dismissSubHint() {
        if (toolTipView != null)
            toolTipView.remove();
    }

    public void dismissHint() {
        showcaseView.hide();
    }

    @Override
    public void onShowcaseViewHide(ShowcaseView showcaseView) {

        if (!currentHint.isPausedHint()) {
            getGamePlayHeaderView().getGamePlayHeaderData().setPaused(false);
            getGamePlayHeaderView().updatePauseView();
        }
        if (isShowSubHint()) {
            currentHint.doSubHint();
        }
    }

    @Override
    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
        Log.i("Showcase", "onShowcaseViewDidHide");
    }

    @Override
    public void onShowcaseViewShow(ShowcaseView showcaseView) {

    }

    public ShowcaseView getShowcaseView() {
        return showcaseView;
    }

    private void handlePausedHint() {
        if (isShowHint()) {
            if (currentHint.isPausedHint()) {
                currentHint.doHint();
            }
        }
    }

    public void setShowcaseView(ShowcaseView showcaseView) {
        this.showcaseView = showcaseView;
    }

    private void handlePlayingHint() {
        if (isShowHint()) {
            currentHint.doHint();
        }

    }

    public GridView getGridView() {
        return this.gridview;
    }

    public class GameLooper extends GamePlayFragment.GameLooper {
        @Override
        protected Object doInBackground(Object[] params) {
            long lastLoopTime = System.nanoTime();

            while (true) {

                if (this.isCancelled()) {
                    Log.i("LOOPER", "Looping Thread cancelled.");
                    break;
                } else {
                    handlePausedHint();
                    if (!gamePlayHeaderView.getGamePlayHeaderData().isPaused()) {
                        // work out how long its been since the last update, this
                        // will be used to calculate how far the entities should
                        // move this loop
                        long now = System.nanoTime();
                        handlePlayingHint();
                        long updateLength = now - lastLoopTime;
                        lastLoopTime = now;
                        double delta = updateLength / ((double) OPTIMAL_TIME);

                        //power up processing.
                        getGamePlayTailView().getGamePlayTailData().getDefaultShopItem().doGameUpdates(GamePlayTutorialFragment.this, delta);
                        getGamePlayTailView().getGamePlayTailData().getDefaultShopItem().doGameRender(GamePlayTutorialFragment.this);

                        //Header Tile Processing
                        gamePlayHeaderView.doGameUpdates(GamePlayTutorialFragment.this, delta);
                        gamePlayHeaderView.doGameRender(GamePlayTutorialFragment.this);

                        //Individual Tile Processing
                        GridView gridView = GamePlayTutorialFragment.this.getGridView();
                        for (int j = gridView.getAdapter().getCount() - 1; j >= 0; j--) {
                            GamePlayPersonTile tile = (GamePlayPersonTile) gridView.getAdapter().getItem(j);
                            tile.doGameUpdates(GamePlayTutorialFragment.this, delta);
                            tile.doGameRender(GamePlayTutorialFragment.this);
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
}
