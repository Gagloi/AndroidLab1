package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            MenuFragment menuFragment = new MenuFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, menuFragment)
                    .commit();
        }
    }
}