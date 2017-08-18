package com.chalknpaper.popularmovies;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by samarsingh on 18/08/17.
 */

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
