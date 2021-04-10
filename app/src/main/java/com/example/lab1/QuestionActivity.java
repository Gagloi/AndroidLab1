package com.example.lab1;

import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class QuestionActivity extends AppCompatActivity implements RetainFragment.ViewStateListener{

    private static final String KEY = "USER";
    private UserDto user;

    private RetainFragment retainFragment;

    private OracleService service;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = ((OracleService.OracleServiceBinder) binder)
                    .getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);

        retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(RetainFragment.TAG);
        if(retainFragment==null){
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment, RetainFragment.TAG).commit();
        }

        Bundle arguments = getIntent().getExtras();

        UserDto user;
        if(arguments!=null) {
            user = arguments.getParcelable(KEY);
            EditText editText = this.findViewById(R.id.questionEdit);
            Intent intent = new Intent(this, OracleService.class);

            bindService(intent, connection, BIND_AUTO_CREATE);

            findViewById(R.id.answerButton).setOnClickListener(v -> {
                if (service == null) return;
//                String answer = service.convert(editText.getText().toString() + user.toString());
//                Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
                retainFragment.generateNumber(editText.getText().toString() + user.toString());
            });
            retainFragment.setListener(this);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, OracleService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY, user);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        retainFragment.setListener(null);
    }

    @Override
    public void onNewState(ViewState state) {
        findViewById(R.id.answerButton).setEnabled(state.isButtonEnabled);
        Toast.makeText(this, state.result, Toast.LENGTH_SHORT).show();
    }
}
