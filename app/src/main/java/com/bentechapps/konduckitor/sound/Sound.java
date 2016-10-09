package com.bentechapps.konduckitor.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.data.ApplicationData;
//#D1FFD1 (green), #D6D6F5 (blue), #FFCCCC(red), #FFFFCC(yellow)

/**
 * Created by Daniel on 2/11/2015.
 */
public class Sound {
    //http://stackoverflow.com/questions/19272646/game-sound-effects-in-android/19311820#19311820
    private static final int MAX_NUMBER_OF_STREAMS = 14;
    private static final int EMPTY_SOUND_ID = 0;
    private static final int BUTTON_CLICK = 1;
    private static final int CAR_STARTING = 2;
    private static final int APPLAUSE = 3;
    private static final int PASSENGER_ALREADY_PAID = 4;
    private static final int PASSENGER_EXIT = 5;
    private static final int PASSENGER_PATIENCE_EXHAUSTED = 6;
    private static final int POWER_UP = 7;
    private static final int RETURN_CHANGE = 8;
    private static final int REPLAY = 9;
    private static final int RETRIEVE_PASSENGER_CASH = 10;
    private static final int SELECT_MONEY = 11;
    private static final int POWER_DOWN = 12;
    private static ApplicationData applicationData;
    private static int sfxIds[];
    private static MediaPlayer drivingByPlayer;
    private static MediaPlayer gameOverPlayer;
    private static MediaPlayer gamePlayPlayer;
    private static int sfxLoadedCount;
    private static int musicLoadedCount;
    private static SoundPool sp;


    public static void releaseSoundPool() {
        sp.release();
        sp = null;
    }

    public static void releaseMediaPlayers() {
        drivingByPlayer.release();
        drivingByPlayer = null;
        gameOverPlayer.release();
        gameOverPlayer = null;
        gamePlayPlayer.release();
        gamePlayPlayer = null;
    }

    public static MediaPlayer newMediaPlayer(Context context, int resourceId) {
        return MediaPlayer.create(context, resourceId);
    }

    private static void initApplicationData(Context context) {
        applicationData = ApplicationData.getInstance(context);
    }

    public static MediaPlayer getLoopingTrack(Context context, int resourceId) {
        initApplicationData(context);
        MediaPlayer mediaPlayer = MediaPlayer.create(context, resourceId);
        mediaPlayer.setLooping(true);
        return mediaPlayer;
    }

    public static MediaPlayer getTrack(Context context, int resourceId) {
        initApplicationData(context);
        MediaPlayer mediaPlayer = MediaPlayer.create(context, resourceId);
        return mediaPlayer;
    }

    public static void playEmptySfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[EMPTY_SOUND_ID], 1, 1, 1, 0, 1.0f);
    }

    public static void playApplauseSfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[APPLAUSE], 1, 1, 1, 0, 1.0f);
    }

    public static void playButtonClickSfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[BUTTON_CLICK], 1, 1, 1, 0, 1.0f);
    }

    public static void playSelectMoneySfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[SELECT_MONEY], 1, 1, 1, 0, 1.0f);
    }

    public static void playRetrievePassengerCashSfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[RETRIEVE_PASSENGER_CASH], 1, 1, 1, 0, 1.0f);
    }

    public static void playReplaySfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[REPLAY], 1, 1, 1, 0, 1.0f);
    }

    public static void playReturnChangeSfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[RETURN_CHANGE], 1, 1, 1, 0, 1.0f);
    }

    public static void playPowerUpSfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[POWER_UP], 1, 1, 1, 0, 1.0f);
    }

    public static void playPassengerPatienceExhaustedSfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[PASSENGER_PATIENCE_EXHAUSTED], 1, 1, 1, 0, 1.0f);
    }

    public static void playPassengerExitSfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[PASSENGER_EXIT], 1, 1, 1, 0, 1.0f);
    }

    public static void playPassengerAlreadyPaidSfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[PASSENGER_ALREADY_PAID], 1, 1, 1, 0, 1.0f);
    }

    public static void playCarStartingSfx() {
        if (applicationData.isSfx())
            sp.play(sfxIds[CAR_STARTING], 1, 1, 1, 0, 1.0f);
    }

    public static void playDrivingByMusic() {
        if(applicationData.isMusic())
            drivingByPlayer.start();
    }
    public static void stopDrivingByMusic() {
        if (drivingByPlayer.isPlaying()) {
            drivingByPlayer.pause();
        }
    }

    public static void playGameOverMusic() {
        if(applicationData.isMusic())
            gameOverPlayer.start();
    }
    public static void stopGameOverMusic() {
        if (gameOverPlayer.isPlaying()) {
            gameOverPlayer.pause();
        }
    }

    public static void playGamePlayMusic() {
        if(applicationData.isMusic())
            gamePlayPlayer.start();
    }
    public static void stopGamePlayMusic() {
        if (gamePlayPlayer.isPlaying()) {
            gamePlayPlayer.pause();
        }
    }

    public static void initializeGamePlaySounds(Context context) {
        musicLoadedCount = sfxLoadedCount = 0;
        initApplicationData(context);
        sp = new SoundPool(MAX_NUMBER_OF_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sfxIds = new int[13];
        sfxIds[EMPTY_SOUND_ID] = sp.load(context, R.raw.empty, 1);
        sfxIds[BUTTON_CLICK] = sp.load(context, R.raw.button_click, 1);
        sfxIds[CAR_STARTING] = sp.load(context, R.raw.car_starting, 1);
        sfxIds[APPLAUSE] = sp.load(context, R.raw.applause, 1);
        sfxIds[PASSENGER_ALREADY_PAID] = sp.load(context, R.raw.passenger_already_paid, 1);
        sfxIds[PASSENGER_EXIT] = sp.load(context, R.raw.passenger_exit, 1);
        sfxIds[PASSENGER_PATIENCE_EXHAUSTED] = sp.load(context, R.raw.passenger_patience_exhausted, 1);
        sfxIds[POWER_UP] = sp.load(context, R.raw.power_up, 1);
        sfxIds[RETURN_CHANGE] = sp.load(context, R.raw.return_change, 1);
        sfxIds[REPLAY] = sp.load(context, R.raw.replay, 1);
        sfxIds[RETRIEVE_PASSENGER_CASH] = sp.load(context, R.raw.retrieve_passenger_cash, 1);
        sfxIds[SELECT_MONEY] = sp.load(context, R.raw.select_money, 1);
        sfxIds[POWER_DOWN] = sp.load(context, R.raw.power_down, 1);
        sp.setOnLoadCompleteListener(new thisUnloadCompleteListener());

        drivingByPlayer = getLoopingTrack(context, R.raw.driving_by);
        gameOverPlayer = getTrack(context, R.raw.game_over);
        gamePlayPlayer = getLoopingTrack(context, R.raw.game_play);
        drivingByPlayer.setOnPreparedListener(new thisUnPreparedListener());
    }


    private static class thisUnloadCompleteListener implements SoundPool.OnLoadCompleteListener {
        @Override
        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
            if (status == 0) {
                sfxLoadedCount++;
                if (sfxLoadedCount == sfxIds.length) {

                }
            }
        }
    }

    private static class thisUnPreparedListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            musicLoadedCount++;
            if (musicLoadedCount == 3) {

            }
        }
    }
}
