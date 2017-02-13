package com.bentechapps.konduckitor.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.activity.fragments.GamePlayFragment;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.data.GamePlayTailData;
import com.bentechapps.konduckitor.model.denomination.unit.DenominationUnit;
import com.bentechapps.konduckitor.model.shop.ShopItem;
import com.bentechapps.konduckitor.sound.Sound;
import com.bentechapps.konduckitor.view.animation.AnimationFactory;
import com.bentechapps.konduckitor.view.custom.WalletButton;

import java.util.ArrayList;

/**
 * Created by BenTech on 2/1/2015.
 */
public class GamePlayTailView extends LinearLayout implements View.OnClickListener {
    protected final GamePlayFragment gamePlayFragment;
    private final ApplicationData appData;
    protected WalletButton fiftiesButton;
    protected ImageView powerUp;
    private WalletButton fivesButton;
    private WalletButton tensButton;
    protected WalletButton twentiesButton;
    private WalletButton hundredsButton;
    private Button selectButton;
    private ArrayList<Integer> selectActions;
    private GamePlayTailData gamePlayTailData;
    private HorizontalScrollView scrollView;
    private boolean isUnselect;
    private TextView powerUpCount;
    private ImageView clearSelectedImage;

    public GamePlayTailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        appData = ApplicationData.getInstance();
        gamePlayFragment = (GamePlayFragment) ((MainActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tail_tile, this, true);
        init();
    }

    private void init() {
        selectActions = new ArrayList();
        this.fivesButton = (WalletButton) findViewById(R.id.fives);
        this.tensButton = (WalletButton) findViewById(R.id.tens);
        this.twentiesButton = (WalletButton) findViewById(R.id.twenties);
        this.fiftiesButton = (WalletButton) findViewById(R.id.fifties);
        this.hundredsButton = (WalletButton) findViewById(R.id.hundreds);
        this.selectButton = (Button) findViewById(R.id.selected);
        this.powerUp = (ImageView) findViewById(R.id.powerUp);
        this.powerUpCount = (TextView) findViewById(R.id.powerUpCount);
        this.clearSelectedImage = (ImageView) findViewById(R.id.clear_selected_image);
//        this.scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);

        fivesButton.setOnClickListener(this);
        tensButton.setOnClickListener(this);
        twentiesButton.setOnClickListener(this);
        fiftiesButton.setOnClickListener(this);
        hundredsButton.setOnClickListener(this);
        selectButton.setOnClickListener(this);
        powerUpCount.setOnClickListener(this);
        powerUp.setOnClickListener(this);
        clearSelectedImage.setOnClickListener(this);

    }

    public void incrementDenominationUnitCount(final DenominationUnit unit, short offset) {
        final WalletButton button = (WalletButton) findViewById(unit.getWalletButtonId());
        button.getDenominationUnit().incrementCount(offset);
        button.post(new Runnable() {
            @Override
            public void run() {
                button.setText(button.getDenominationUnit().toString());
            }
        });
    }

    public void decrementDenominationUnitCount(final WalletButton button, final DenominationUnit denominationUnit, final short offset) {
        denominationUnit.decrementCount(offset);
        button.post(new Runnable() {
            @Override
            public void run() {
                button.setText(denominationUnit.toString());
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (gamePlayFragment.getGamePlayHeaderView().getGamePlayHeaderData().isPaused()) {
            return;
        }
        switch (v.getId()) {
            case R.id.selected:
            case R.id.clear_selected_image:
                handleUnselect();
                break;
            case R.id.fives:
            case R.id.tens:
            case R.id.twenties:
            case R.id.fifties:
            case R.id.hundreds:
                handleWalletDenominationClick((WalletButton) v);
                break;
            case R.id.powerUp:
            case R.id.powerUpCount:
                handlePowerUps();
                break;
        }
    }

    private void handlePowerUps() {
        ShopItem item = ShopItem.list(getContext()).get(appData.getDefaultPowerUp());
        if (item.getHave() > 0) {
            AnimationSet powerUpAnimation = AnimationFactory.newRotateAnimation(Math.max((item.getDuration() / GamePlayFragment.TARGET_FPS) * 2, 1));
            powerUpAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    powerUp.post(new Runnable() {
                        @Override
                        public void run() {
                            powerUp.setEnabled(false);
                        }
                    });
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    powerUp.post(new Runnable() {
                        @Override
                        public void run() {
                            powerUp.setEnabled(true);
                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            powerUp.startAnimation(powerUpAnimation);
            Sound.playPowerUpSfx();
            gamePlayFragment.getGamePlayFragmentData().setPowerUpMode(true);
            gamePlayFragment.getGamePlayFragmentData().setPowerUpDuration(0);
            item.decrementHave(1);
            powerUpCount.setText(String.valueOf(item.getHave()));
            appData.savePreference();
        } else {
            Sound.playEmptySfx();
            powerUp.startAnimation(AnimationFactory.newWobbleAnimation());
        }
    }

    private void handleWalletDenominationClick(WalletButton walletButton) {
        if (!walletButton.getDenominationUnit().isEmpty()) {
            decrementDenominationUnitCount(walletButton, walletButton.getDenominationUnit(), (short) 1);
            incrementSelected(walletButton.getDenominationUnit().getValue());
            selectActions.add(walletButton.getId());
            Sound.playSelectMoneySfx();
        } else {
            Sound.playEmptySfx();
            walletButton.startAnimation(AnimationFactory.newWobbleAnimation());
        }
    }

    private void handleUnselect() {
        Sound.playEmptySfx();
        for (int i : selectActions) {
            switch (i) {
                case R.id.fives:
                case R.id.tens:
                case R.id.twenties:
                case R.id.fifties:
                case R.id.hundreds:
                    incrementDenominationUnitCount(((WalletButton) findViewById(i)).getDenominationUnit(), (short) 1);
                    break;
            }
        }
        isUnselect = true;
        clearSelected();

    }

    public GamePlayTailData getGamePlayTailData() {
        return gamePlayTailData;
    }

    public void setGamePlayTailData(GamePlayTailData gamePlayTailData) {
        this.gamePlayTailData = gamePlayTailData;
        initWalletButtons(gamePlayTailData);
        selectButton.setText(String.valueOf(gamePlayTailData.getSelectedAmount()));
        updatePowerUpView();
        this.fivesButton.setDenominationUnit(gamePlayTailData.getConductorWallet().getFives());
        this.tensButton.setDenominationUnit(gamePlayTailData.getConductorWallet().getTens());
        this.twentiesButton.setDenominationUnit(gamePlayTailData.getConductorWallet().getTwenties());
        this.fiftiesButton.setDenominationUnit(gamePlayTailData.getConductorWallet().getFifties());
        this.hundredsButton.setDenominationUnit(gamePlayTailData.getConductorWallet().getHundreds());
        isUnselect = false;
    }

    public void updatePowerUpView() {
        powerUp.post(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                powerUp.setImageDrawable(gamePlayTailData.getDefaultShopItem().getImage());
            }
        });

        powerUpCount.setText(String.valueOf(gamePlayTailData.getDefaultShopItem().getHave()));
    }

    private void initWalletButtons(GamePlayTailData gamePlayTailData) {
        fivesButton.setText(gamePlayTailData.getConductorWallet().getFives().toString());
        tensButton.setText(String.valueOf(gamePlayTailData.getConductorWallet().getTens()));
        twentiesButton.setText(String.valueOf(gamePlayTailData.getConductorWallet().getTwenties()));
        fiftiesButton.setText(String.valueOf(gamePlayTailData.getConductorWallet().getFifties()));
        hundredsButton.setText(String.valueOf(gamePlayTailData.getConductorWallet().getHundreds()));
    }

    private int incrementSelected(int offset) {
        gamePlayTailData.incrementSelectedAmount(offset);
        selectButton.post(new Runnable() {
            @Override
            public void run() {
                selectButton.setText(String.valueOf(gamePlayTailData.getSelectedAmount()));
            }
        });
        return gamePlayTailData.getSelectedAmount();
    }

    private int decrementSelected(int offset) {
        gamePlayTailData.decrementSelectedAmount(offset);
        selectButton.post(new Runnable() {
            @Override
            public void run() {
                selectButton.setText(String.valueOf(gamePlayTailData.getSelectedAmount()));
            }
        });
        return gamePlayTailData.getSelectedAmount();
    }

    public int clearSelected() {
        selectButton.post(new Runnable() {
            @Override
            public void run() {
                selectButton.setText("0");
            }
        });
        if (!isUnselect) {//if NOT clear wallet selection buttons
            //wallet was deducted, keep track of denominations deducted for mission purposes.
            handleMissionWalletUseTracker();
        }
        isUnselect = false;
        selectActions.clear();
        return gamePlayTailData.clearSelectedAmount();
    }

    private void handleMissionWalletUseTracker() {
        for (int i : selectActions) {
            switch (i) {
                case R.id.fives:
                    gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementFivesUseCount(1);
                    break;
                case R.id.tens:
                    gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementTensUseCount(1);
                    break;
                case R.id.twenties:
                    gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementTwentiesUseCount(1);
                    break;
                case R.id.fifties:
                    gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementFiftiesUseCount(1);
                    break;
                case R.id.hundreds:
                    gamePlayFragment.getGamePlayFragmentData().getMissionInfoHolder().incrementHundredsUseCount(1);
                    break;
            }
        }
    }

    public Button getSelectButton() {
        return selectButton;
    }

    public void setSelectButton(Button selectButton) {
        this.selectButton = selectButton;
    }

    public void doWalletScroll() {
        return;
//        scrollView.post(new Runnable() {
//            @Override
//            public void run() {
//                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
//            }
//        });
    }


    public void doClearWalletFirstHint() {
        clearSelectedImage.startAnimation(AnimationFactory.newWobbleAnimation());
    }
}
