package com.example.stepcounter.Components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import com.example.stepcounter.BuildConfig;
import com.example.stepcounter.Database.Database;
import com.example.stepcounter.Utils.API26Wrapper;
import com.example.stepcounter.Utils.Logger;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (BuildConfig.DEBUG) Logger.log("booted");

        SharedPreferences prefs = context.getSharedPreferences("pedometer", Context.MODE_PRIVATE);

        Database db = Database.getInstance(context);

        if (!prefs.getBoolean("correctShutdown", false)) {
            if (BuildConfig.DEBUG) Logger.log("Incorrect shutdown");
            // can we at least recover some steps?
            int steps = Math.max(0, db.getCurrentSteps());
            if (BuildConfig.DEBUG) Logger.log("Trying to recover " + steps + " steps");
            db.addToLastEntry(steps);
        }
        db.removeNegativeEntries();
        db.saveCurrentSteps(0);
        db.close();
        prefs.edit().remove("correctShutdown").apply();

        if (Build.VERSION.SDK_INT >= 26) {
            API26Wrapper.startForegroundService(context, new Intent(context, SensorListener.class));
        } else {
            context.startService(new Intent(context, SensorListener.class));
        }
    }
}
