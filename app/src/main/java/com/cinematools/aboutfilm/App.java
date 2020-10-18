package com.cinematools.aboutfilm;

import android.app.Application;
import android.content.Context;


import com.cinematools.aboutfilm.model.manager.factory.FilmsFactory;
import com.cinematools.aboutfilm.model.manager.factory.FilmsManager;


public class App extends Application {

    private FilmsManager manager;

    public static FilmsManager getManager(Context context) {
        return getApp(context).manager;
    }

    private static App getApp(Context context) {

        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = FilmsFactory.createFilmsManager(this);
    }
}
