package com.example.lab1;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;


public class ComputationHandlerThread extends HandlerThread {

    private static final String TAG = "ComputationHandlerThread";
    public static final int EXAMPLE_TASK = 1;
    private Handler handler;

    public ComputationHandlerThread() {
        super(TAG, Process.THREAD_PRIORITY_BACKGROUND);
    }

    @Override
    protected void onLooperPrepared() {
        handler = new Handler(this.getLooper());
    }

    public void postTask(Runnable task) {
        handler.post(task);
    }

}
