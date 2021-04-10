package com.example.lab1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab1.tasks.ComputationCallable;
import com.example.lab1.tasks.HandlerThreadTask;
import com.example.lab1.tasks.Task;
import com.example.lab1.tasks.TaskListener;

public class RetainFragment extends Fragment {



    private ViewState viewState = new ViewState();
    private  ViewStateListener listener;
    public static final String TAG = RetainFragment.class.getSimpleName();
    private Task<String> currentTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    public void setListener(ViewStateListener listener)
    {
        this.listener=listener;
        if(listener!=null)
        {
            listener.onNewState(viewState);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(currentTask!=null)
        {
            currentTask.cancel();
        }
    }

    public void generateNumber(String b)
    {
        currentTask= createGeneratorNumberTask(b);
        viewState.isButtonEnabled=false;
        viewState.showResult=false;
        viewState.result="";

        updateState();
        currentTask.execute(new TaskListener<String>() {
            @Override
            public void onSuccess(String result) {
                viewState.isButtonEnabled = true;
                viewState.showResult = true;
                viewState.result = String.valueOf(result);
                updateState();
            }

            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Error", error );
                viewState.isButtonEnabled = true;
                viewState.showResult = false;
                viewState.result = "";
                updateState();
            }
        });
    }

    private  void updateState()
    {
        if(listener!=null)
        {
            listener.onNewState(viewState);
        }
    }
    private Task<String> createGeneratorNumberTask(String b)
    {
        return new HandlerThreadTask<>(new ComputationCallable(b));
    }
    public interface ViewStateListener
    {
        void onNewState(ViewState state);
    }
}
