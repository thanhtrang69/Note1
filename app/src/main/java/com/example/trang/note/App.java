package com.example.trang.note;

import android.app.Application;
import android.content.Context;

/**
 * Created by Trang on 5/5/2017.
 */

public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        this.context = this;
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }
}
