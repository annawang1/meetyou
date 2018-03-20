package com.example.annawang.meetyou;

import android.app.Application;

import com.example.annawang.meetyou.util.CrashHandler;

import org.androidannotations.annotations.EApplication;

@EApplication
public class MeetYouApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }

}
