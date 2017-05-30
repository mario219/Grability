package com.mario219.grability.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by marioalejndro on 25/01/17.
 */

public class ConnectivityBehavior extends BroadcastReceiver {

    public static boolean online;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if(nInfo != null && nInfo.isConnectedOrConnecting()){
            online = true;
            Log.i("Network available", "flag1");
        }else{
            online = false;
            Log.i("Network lost", "flag2");
        }
    }

    public static Boolean isOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if(nInfo != null && nInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

}
