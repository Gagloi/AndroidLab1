package com.example.lab1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button temperatureConverterButton = (Button) view.findViewById(R.id.temperatureConverter);
        Button weightConverterButton = (Button) view.findViewById(R.id.weightConverter);
        Button lengthConverterButton = view.findViewById(R.id.lengthConverter);

        temperatureConverterButton.setOnClickListener(it ->
        {
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, new TemperatureConverterFragment())
                    .commit();
        });
        weightConverterButton.setOnClickListener(it ->
        {
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, new WeightConverterFragment())
                    .commit();
        });
        lengthConverterButton.setOnClickListener(it ->
        {
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, new LengthsConverterFragment())
                    .commit();
        });


    }
}