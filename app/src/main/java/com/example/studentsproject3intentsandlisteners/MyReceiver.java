package com.example.studentsproject3intentsandlisteners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d ("Send", "Received a broadcast message: "+intent.getExtras().getString("msg"));
        displayToast("I received a broadcast", context);
    }
    public void displayToast (String text, Context context) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}