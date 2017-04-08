package com.bentechapps.konduckitor.activity.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bentech.android.appcommons.fragment.AppCommonsFragment;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.util.SystemUiHider;
import com.bentechapps.konduckitor.app.Constants;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayFragmentData;
import com.bentechapps.konduckitor.data.GamePlayHeaderData;
import com.bentechapps.konduckitor.data.GamePlayTailData;
import com.bentechapps.konduckitor.model.denomination.impl.DefaultConductorWalletDenomination;
import com.bentechapps.konduckitor.model.person.Passenger;
import com.bentechapps.konduckitor.model.person.PassengerFactory;
import com.bentechapps.konduckitor.model.person.PassengerState;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.GamePlayHeaderView;
import com.bentechapps.konduckitor.view.GamePlayTailView;
import com.bentechapps.konduckitor.view.adapter.PassengersAdapter;
import com.bentechapps.konduckitor.view.custom.PauseGameDialog;
import com.bentechapps.konduckitor.view.custom.StartMissionDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class GamePlayFragment extends AppCommonsFragment {
    public static final int TARGET_FPS = 10;
    public static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    private static final String TAG = GamePlayFragment.class.getSimpleName();
    private static final String GAME_PLAY_FRAGMENT_DATA = "GAME_PLAY_FRAGMENT_DATA";
    protected GamePlayHeaderView gamePlayHeaderView;
    protected GameLooper loop;
    protected GamePlayFragmentData gamePlayFragmentData;
    protected ApplicationData appData;
    protected GamePlayTailView gamePlayTailView;
    @BindView(R.id.passengersRecyclerView)
    RecyclerView passengersRecyclerView;
    private PassengersAdapter passengersAdapter;
    private List<Passenger> passengers;
    private List<Passenger> fullPassengersList;
    private ListIterator<Passenger> fullPassengersListIterator;
    private DefaultConductorWalletDenomination conductorWallet;
    private GamePlayHeaderData gamePlayHeaderData;


    public static void handleRestartAndNextMissionInit(GamePlayFragment gamePlayFragment, boolean fullRestart) {
        GamePlayFragmentData gamePlayFragmentData = new GamePlayFragmentData();
        if (gamePlayFragment.getGamePlayFragmentData().isMissionMode()) {
            gamePlayFragmentData.setCurrentLevel(gamePlayFragment.getGamePlayFragmentData().getCurrentLevel());
            gamePlayFragmentData.getCurrentLevel().getMissionInfoHolder().resetAllExceptScore();
            gamePlayFragmentData.setMissionInfoHolder(gamePlayFragmentData.getCurrentLevel().getMissionInfoHolder());
            gamePlayFragmentData.setCurrentMission(gamePlayFragment.getGamePlayFragmentData().getCurrentMission());
            gamePlayFragmentData.setIsMissionMode(true);
        }

        //Todo review and remove this if not necessary.
        if (gamePlayFragment.getArguments() != null) {
            gamePlayFragment.getArguments().putSerializable(GAME_PLAY_FRAGMENT_DATA, gamePlayFragmentData);
        }

        if (gamePlayFragmentData.isMissionMode()) {
            gamePlayFragmentData.setCurrentMission(gamePlayFragmentData.getCurrentMission().restartMission());
        }

        if (fullRestart) {
            for (Passenger passenger : gamePlayFragment.passengers) {
                passenger.recycle();
            }
        }

    }

    public static GamePlayFragment newInstance(GamePlayFragmentData gamePlayFragmentData) {

        Bundle args = new Bundle();

        args.putSerializable(GAME_PLAY_FRAGMENT_DATA, gamePlayFragmentData);

        GamePlayFragment fragment = new GamePlayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public GamePlayFragmentData getGamePlayFragmentData() {
        return gamePlayFragmentData;
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

        if (getArguments() != null && getArguments().getSerializable(GAME_PLAY_FRAGMENT_DATA) != null) {
            this.gamePlayFragmentData = (GamePlayFragmentData) getArguments().getSerializable(GAME_PLAY_FRAGMENT_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_game_play, container, false);
        ButterKnife.bind(this, rootView);

        if (passengersAdapter == null) {
            fullPassengersList = PassengerFactory.listPassengers(gamePlayFragmentData.getCurrentLevel());
            passengersAdapter = new PassengersAdapter(
                    onPassengerClicked(),
                    appCommonsActivity,
                    passengers = new ArrayList<>(fullPassengersList.subList(0, Constants.MAX_PASSENGERS))
            );
        }
        fullPassengersListIterator = fullPassengersList.listIterator(Constants.MAX_PASSENGERS);
        passengersRecyclerView.setAdapter(passengersAdapter);
        passengersRecyclerView.setLayoutManager(new GridLayoutManager(appCommonsActivity, 4));
        return rootView;
    }

    List<Passenger> getPassengers() {
        return passengers;
    }

    List<Passenger> getFullPassengersList() {
        return fullPassengersList;
    }

    private View.OnClickListener onPassengerClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedPosition = passengersRecyclerView.getChildAdapterPosition(v);
                Passenger selectedPassenger = passengers.get(selectedPosition);

                if (gamePlayHeaderView.getGamePlayHeaderData().isPaused()) {
                    return;
                }

                boolean isCredit = gamePlayTailView.getGamePlayTailData().getSelectedAmount() > 0;
                if (isCredit) {
                    handleCredit(selectedPassenger);
                } else {
                    handleDebit(selectedPassenger);
                }
            }
        };
    }

    protected void handleCredit(Passenger selectedPassenger) {
        if (!selectedPassenger.getAmountToPay().isEmpty()) {
            //can't give back change without collecting money first
            gamePlayTailView.doClearWalletFirstHint();
            Sound.playEmptySfx();
            return;
        }

        Sound.playReturnChangeSfx();
        int credit = gamePlayTailView.getGamePlayTailData().getSelectedAmount();

        selectedPassenger.getAmountWith().incrementValue((short) credit);
        selectedPassenger.getAmountWith().incrementCount((short) 1);

        gamePlayTailView.clearSelected();
        if (selectedPassenger.getAmountWith().getValue() > 0) {
            //punish player for giving more change than expected
            gamePlayHeaderView.decrementLife(Constants.EXCESS_CHANGE_PENALTY);
            gamePlayFragmentData.getMissionInfoHolder().incrementNumberOfOverpaidPassengers(1);
        }

        if (selectedPassenger.getAmountWith().getValue() >= 0 && selectedPassenger.getPassengerState() != PassengerState.SETTLED) {
            if (selectedPassenger.isMale()) {
                gamePlayFragmentData.getMissionInfoHolder().incrementNumberOfSettledMales(1);
            } else {
                gamePlayFragmentData.getMissionInfoHolder().incrementNumberOfSettledFemales(1);
            }
            gamePlayFragmentData.getMissionInfoHolder().incrementTotalAmountPaidOut(credit);
            selectedPassenger.setPassengerState(PassengerState.SETTLED);
        }
    }


    protected void handleDebit(Passenger selectedPassenger) {
        if (selectedPassenger.getAmountToPay().isEmpty()) {
            Sound.playPassengerAlreadyPaidSfx();
        } else {
            Sound.playRetrievePassengerCashSfx();
            gamePlayFragmentData.getMissionInfoHolder().incrementTotalAmountCollected(selectedPassenger.getAmountWith().getValue());
            gamePlayTailView.incrementDenominationUnitCount(selectedPassenger.getAmountWith(), (short) 1);

            //decrement amount with
            selectedPassenger.getAmountWith().decrementValue((short) (selectedPassenger.getAmountWith().getValue() + (selectedPassenger.getAmountWith().getValue() - selectedPassenger.getAmountToPay().getValue())));
            selectedPassenger.getAmountWith().decrementCount((short) 1);

            //decrement amount to pay
            selectedPassenger.getAmountToPay().decrementValue(selectedPassenger.getAmountToPay().getValue());
            selectedPassenger.getAmountToPay().decrementCount((short) 1);


            if (selectedPassenger.getAmountWith().getValue() == 0) {
                gamePlayFragmentData.getMissionInfoHolder().incrementExactFarePassengers(1);
                if (selectedPassenger.isMale()) {
                    gamePlayFragmentData.getMissionInfoHolder().incrementNumberOfSettledMales(1);
                } else {
                    gamePlayFragmentData.getMissionInfoHolder().incrementNumberOfSettledFemales(1);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeGame();
    }

    public void initializeGame() {
        appData = ApplicationData.getInstance();
        initUI();
        init();
    }

    protected void initUI() {
        gamePlayHeaderView = (GamePlayHeaderView) getView().findViewById(R.id.game_play_header_view);
        gamePlayTailView = (GamePlayTailView) getView().findViewById(R.id.game_play_tail_view);
        if (gamePlayFragmentData.isMissionMode()) {
            StartMissionDialog startMissionDialog = new StartMissionDialog(getActivity(), getGamePlayFragmentData().getCurrentMission());
            startMissionDialog.show();
        } else {
            PauseGameDialog pauseGameDialog = new PauseGameDialog(getActivity(), null);
            pauseGameDialog.show();
        }
    }

    protected void init() {
        if (conductorWallet == null) {
            conductorWallet = new DefaultConductorWalletDenomination(getActivity());
        }

        if (gamePlayHeaderData == null) {
            gamePlayHeaderData = GamePlayHeaderData.newGamePlayHeaderData(true, 0, GamePlayHeaderData.MAX_LIFE, 0);
        } else {
            gamePlayHeaderData.reset();
        }

        GamePlayTailData gamePlayTailData = GamePlayTailData.newGamePlayTailData(conductorWallet, 0, ShopItem.list(getActivity()).get(appData.getDefaultPowerUp()));
        gamePlayTailView.setGamePlayTailData(gamePlayTailData);

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

    public PassengersAdapter getPassengersAdapter() {
        return passengersAdapter;
    }

    public class GameLooper extends AsyncTask {

        @SuppressWarnings("WrongThread")
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
                        Passenger passenger;
                        for (int i = 0; i < passengers.size(); i++) {

                            passenger = passengers.get(i);

                            passenger.doGameUpdates(GamePlayFragment.this, delta);

                            passenger.doGameRender(GamePlayFragment.this);
                            if (passenger.isLowPatienceSignalled()) {
                                passengersAdapter.update(i, passenger);
                            }
                            if (passenger.isRecycle()) {

                                if (!fullPassengersListIterator.hasNext()) {
                                    fullPassengersListIterator = fullPassengersList.listIterator();
                                }

                                passenger = fullPassengersListIterator.next();
                                passenger.recycle();

                                passengers.set(i, passenger);
                            }

                            passengersAdapter.update(i, passenger);
                            final int finalI = i;
                            GamePlayFragment.this.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    passengersAdapter.notifyItemChanged(finalI);
                                }
                            });

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
