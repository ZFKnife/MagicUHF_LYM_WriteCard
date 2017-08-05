package com.fxb.writecard;

import android.app.Application;
import android.content.Context;

import com.olc.uhf.UhfAdapter;
import com.olc.uhf.UhfManager;

public class App extends Application {


    public static UhfManager mService;

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        mService = UhfAdapter.getUhfManager(this.getApplicationContext());
        boolean isopen = mService.open();
        int b = 0;
        mContext = getApplicationContext();

    }
}
