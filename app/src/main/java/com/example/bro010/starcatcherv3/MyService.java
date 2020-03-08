package com.example.bro010.starcatcherv3;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by bro016 on 21/03/2018.
 */

public class MyService extends Service {
    private MediaPlayer mp;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand (Intent intent,int flags, int startId){
        mp=MediaPlayer.create(this,R.raw.mainamnu);
        mp.setLooping(true);
        mp.start();
        return START_STICKY;
    }
    public void onDestroy()
    {
        super.onDestroy();
        mp.stop();
    }


}