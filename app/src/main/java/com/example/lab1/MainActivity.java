package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private final static String TAG = MainActivity.class.getSimpleName();

    private UserDto user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        Button questionButton = this.findViewById(R.id.askButton);
        Button settingsButton = this.findViewById(R.id.settingsButton);
        Button exitButton = this.findViewById(R.id.exitButton);

        Bundle arguments = getIntent().getExtras();

        questionButton.setOnClickListener(v -> {
                if (arguments != null)
                user = arguments.getParcelable("USER");
                Intent intent = new Intent(this, QuestionActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
        });

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("USER", "USER");
            startActivity(intent);
        });

        exitButton.setOnClickListener(v -> {
            System.exit(0);
        });

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
//        if(savedInstanceState == null) {
//            MenuFragment menuFragment = new MenuFragment();
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragmentContainer, menuFragment)
//                    .commit();
//        }
    }

//    private ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            OracleService.ConvertationServiceBinder binder = (OracleService.ConvertationServiceBinder) service;
//            convertationService = binder.getService();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            convertationService = null;
//        }
//    };
}