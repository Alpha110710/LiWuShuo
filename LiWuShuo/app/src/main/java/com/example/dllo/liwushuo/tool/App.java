package com.example.dllo.liwushuo.tool;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/5/23.
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
