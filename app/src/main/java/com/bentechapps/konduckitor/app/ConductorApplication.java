package com.bentechapps.konduckitor.app;

import android.app.Application;
import android.content.Context;

import com.bentech.android.appcommons.AppCommons;
import com.bentechapps.konduckitor.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by bendaniel on 29/10/2016.
 */

public class ConductorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SF_Atarian_System.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        AppCommons.install(this);
    }
}

