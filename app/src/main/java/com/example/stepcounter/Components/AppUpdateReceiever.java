package com.example.stepcounter.Components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.example.stepcounter.BuildConfig;
import com.example.stepcounter.Utils.API26Wrapper;
import com.example.stepcounter.Utils.Logger;

public class AppUpdateReceiever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(BuildConfig.DEBUG) Logger.log("app updated");
        if(Build.VERSION.SDK_INT>=26){
            API26Wrapper.startForegroundService(context,new Intent(context,SensorListener.class));
        }
        else{
           context.startService(new Intent(context,SensorListener.class));
        }
    }
}
