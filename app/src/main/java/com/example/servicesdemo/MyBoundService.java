package com.example.servicesdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyBoundService extends Service {
    private final IBinder binder = new LocalBinder();
    private final Random generator = new Random();

    public class LocalBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    public int getRandomNumber() {
        return generator.nextInt(100);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
