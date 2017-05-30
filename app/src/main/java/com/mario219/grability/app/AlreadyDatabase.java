package com.mario219.grability.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mario219 on 25/01/17.
 */

public class AlreadyDatabase {

    private static final String TAG = AlreadyDatabase.class.getSimpleName();
    private static String PREF_NAME = "AppsDatabase";

    public AlreadyDatabase(){

    }

    private static SharedPreferences getPrefs (Context context){
        return context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    }

    public static Boolean getDbAlready(Context context){
        SharedPreferences prefs = getPrefs(context);
        Boolean restored = prefs.getBoolean("dbAlready", false);
        if (restored != false) {
            return true;
        }else{
            return false;
        }
    }

    public static void DbAlready (Context context, Boolean dbAlready) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean("dbAlready", dbAlready);
        editor.commit();
        Log.i(TAG, "db created");
    }
}
