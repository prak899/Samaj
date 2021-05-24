package com.siddhi.softwares.sutsamaj;

import android.app.Application;
import android.content.Intent;

import com.siddhi.softwares.sutsamaj.DB.DBManager;

public class App extends Application {
    private DBManager dbManager;

    @Override
    public void onCreate() {
        super.onCreate();

        dbManager = new DBManager(this);
        dbManager.open();
    }
}
