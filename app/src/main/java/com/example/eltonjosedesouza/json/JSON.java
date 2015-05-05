package com.example.eltonjosedesouza.json;

import android.app.Application;
import android.content.Context;

/**
 * Created by eltonjosedesouza on 29/04/15.
 */
public class JSON extends Application {
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        init();
    }

    public static Context getAppContext() {
        return JSON.context;
    }

    private void init() {
        MyVolley.init(this);
    }

}
