package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private final static String TAG = MainActivity.class.getSimpleName();
    private ConvertationService convertationService;

    @Override
    protected void onStart() {
        Intent intent = new Intent(this, ConvertationService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.kek)
//                .setOnClickListener(v -> {
//                    if (convertationService == null) return;
//                    try {
//                        convertationService.convert();
//                        Log.d(TAG, convertationService.getLengths().toString());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Log.d(TAG, "Execute service method");
//                });
        if(savedInstanceState == null) {
            MenuFragment menuFragment = new MenuFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, menuFragment)
                    .commit();
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ConvertationService.ConvertationServiceBinder binder = (ConvertationService.ConvertationServiceBinder) service;
            convertationService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            convertationService = null;
        }
    };
}