package com.example.thibanglai.setting;

import android.app.Application;

public class MyApplication extends Application {

    public static String nameSharedPreference = "APP_STATE";
    public static String nameDB = "databases/DB_LAW";
    public static boolean isChangeEdtInAdapter = false;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
