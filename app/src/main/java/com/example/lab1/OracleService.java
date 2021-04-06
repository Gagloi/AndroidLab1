package com.example.lab1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OracleService extends Service {

    private final static String TAG = OracleService.class.getSimpleName();

    private OracleServiceBinder binder = new OracleServiceBinder();

    public List<String> answerList = Arrays.asList("Так", "Ні", "Можливо", "Цілком вірогідно", "Малоймовірно", "Не знаю");

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");

        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    public String convert(String s) {
        double hash = s.hashCode();
        int key = (int) Math.abs(Math.round((hash / Integer.MAX_VALUE) * answerList.size() - 1));
        return answerList.get(key);
    }

    public class OracleServiceBinder extends Binder {
        public OracleService getService() {
            return OracleService.this;
        }
    }
}
