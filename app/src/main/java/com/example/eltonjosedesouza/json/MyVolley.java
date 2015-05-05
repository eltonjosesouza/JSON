package com.example.eltonjosedesouza.json;

/**
 * Created by eltonjosedesouza on 29/04/15.
 */

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyVolley {

    private static RequestQueue mRequestQueue;


    private MyVolley() {
    }


    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mRequestQueue.getCache().clear();
    }


    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}