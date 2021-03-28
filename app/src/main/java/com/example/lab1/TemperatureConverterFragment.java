package com.example.lab1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab1.temperature.temp.Temperatures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemperatureConverterFragment extends Fragment {

    List<String> temperatures = Arrays.asList("CELSIUS", "FAHRENHEIT", "KELVIN");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature_converter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editText = view.findViewById(R.id.temperatureInputFrom);
        TextView textView = view.findViewById(R.id.temperatureOutputTo);
        Spinner temperatureConverterFrom = (Spinner) view.findViewById(R.id.temperatureConverterFrom);
        Spinner temperatureConverterTo = (Spinner) view.findViewById(R.id.temperatureConverterTo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, temperatures);

        temperatureConverterFrom.setAdapter(adapter);
        temperatureConverterTo.setAdapter(adapter);

        AdapterView.OnItemSelectedListener onItemSelectedListener = changeView(editText, textView, temperatureConverterFrom, temperatureConverterTo);

        Button backButton = view.findViewById(R.id.back);
        backButton.setOnClickListener(it ->
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragmentContainer, new MenuFragment())
                        .commit()
        );
        temperatureConverterFrom.setOnItemSelectedListener(onItemSelectedListener);
        temperatureConverterTo.setOnItemSelectedListener(onItemSelectedListener);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText.getText().toString().isEmpty()) {
                textView.setText("");
                } else {
                    String from = temperatureConverterFrom.getSelectedItem().toString();
                    String to = temperatureConverterTo.getSelectedItem().toString();
                    textView.setText(String.valueOf(Math.round(Temperatures.valueOf(from).convert(Double.parseDouble(editText.getText().toString()), Temperatures.valueOf(to)))));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private AdapterView.OnItemSelectedListener changeView(EditText editText, TextView textView, Spinner temperatureConverterFrom, Spinner temperatureConverterTo) {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (editText.getText().toString().isEmpty()) {
                    textView.setText("");
                } else {
                    String from = temperatureConverterFrom.getSelectedItem().toString();
                    String to = temperatureConverterTo.getSelectedItem().toString();
                    textView.setText(String.valueOf(Math.round(Temperatures.valueOf(from).convert(Double.parseDouble(editText.getText().toString()), Temperatures.valueOf(to)))));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }


}
