package com.example.studentsproject3intentsandlisteners;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

public class MyService extends Service {
    private MediaPlayer player;
    @Override
    public IBinder onBind(Intent intent) {return null;}
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Getting system default ringtone
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        // Setting loop play to true. This will make the ringtone continuously playing
        player.setLooping(true);
        player.start(); //staring the player
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop(); // Stopping the player when service is destroyed
    }

}