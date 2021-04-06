package com.example.lab1;

import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class QuestionActivity extends AppCompatActivity {

    private static final String KEY = "USER";
    private UserDto user;

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

        Bundle arguments = getIntent().getExtras();

        UserDto user;
        if(arguments!=null) {
            user = arguments.getParcelable(KEY);
            EditText editText = this.findViewById(R.id.questionEdit);
            Intent intent = new Intent(this, OracleService.class);

            bindService(intent, connection, BIND_AUTO_CREATE);

            findViewById(R.id.answerButton).setOnClickListener(v -> {
                if (service == null) return;
                String answer = service.convert(editText.getText().toString() + user.toString());
                Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
            });
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


}
