package com.example.rabbit.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyLocalReceiver extends BroadcastReceiver {

    private static final String TAG ="MyLocalReceiver" ;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "-----开始执行Receiver----"+intent.getAction());
    }
}
