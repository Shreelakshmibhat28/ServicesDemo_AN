package com.example.servicesdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyStartedService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            Log.d("StartedService", "Service running...");
            try {
                Thread.sleep(3000); // Simulate task
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stopSelf();
        }).start();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
