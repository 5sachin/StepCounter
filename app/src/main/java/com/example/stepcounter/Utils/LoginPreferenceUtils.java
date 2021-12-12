package com.example.stepcounter.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.stepcounter.Constants.DBConstants;

public class LoginPreferenceUtils {

    public LoginPreferenceUtils(){
    }

    public static boolean saveEmail(String email, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DBConstants.KEY_EMAIL, email);
        editor.apply();
        return true;
    }

    public static String getEmail(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(DBConstants.KEY_EMAIL, null);
    }

    public static boolean savePassword(String password, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DBConstants.KEY_PASSWORD, password);
        editor.apply();
        return true;
    }

    public static String getPassword(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(DBConstants.KEY_PASSWORD, null);
    }

}
