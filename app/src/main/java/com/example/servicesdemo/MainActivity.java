package com.example.servicesdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MyBoundService boundService;
    boolean isBound = false;
    Button startStartedService, startBoundService, startForegroundService;

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder) service;
            boundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         startStartedService = findViewById(R.id.startStartedService);
         startBoundService = findViewById(R.id.startBoundService);
         startForegroundService = findViewById(R.id.startForegroundService);

        startStartedService.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyStartedService.class);
            startService(intent);
        });

        startBoundService.setOnClickListener(v -> {
            if (isBound) {
                int num = boundService.getRandomNumber();
                Log.d("BoundService", "Random Number: " + num);
            }
        });

        startForegroundService.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyForegroundService.class);
            startService(intent);
        });
    }
}
