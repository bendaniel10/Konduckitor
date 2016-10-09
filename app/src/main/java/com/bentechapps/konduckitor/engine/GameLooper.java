package com.bentechapps.konduckitor.engine;

import android.os.AsyncTask;

/**
 * Created by Daniel on 2/5/2015.
 */
public class GameLooper extends AsyncTask{
    private boolean isRunning;
    @Override
    protected Object doInBackground(Object[] params) {
        while (isRunning) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
