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


import com.example.lab1.temperature.temp.Weights;

import java.util.Arrays;
import java.util.List;

public class WeightConverterFragment extends Fragment {

    List<String> weights = Arrays.asList("GRAM", "KILOGRAM", "TONE", "CARAT", "POUND", "PUD");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_converter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editText = view.findViewById(R.id.weightInputFrom);
        TextView textView = view.findViewById(R.id.weightOutputTo);
        Spinner weightConverterFrom = (Spinner) view.findViewById(R.id.weightConverterFrom);
        Spinner weightConverterTo = (Spinner) view.findViewById(R.id.weightConverterTo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, weights);

        weightConverterFrom.setAdapter(adapter);
        weightConverterTo.setAdapter(adapter);

        Button backButton = view.findViewById(R.id.back);
        backButton.setOnClickListener(it ->
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragmentContainer, new MenuFragment())
                        .commit()
        );
        AdapterView.OnItemSelectedListener onItemSelectedListener = changeView(editText, textView, weightConverterFrom, weightConverterTo);

        weightConverterFrom.setOnItemSelectedListener(onItemSelectedListener);
        weightConverterTo.setOnItemSelectedListener(onItemSelectedListener);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText.getText().toString().isEmpty()) {
                    textView.setText("");
                } else {
                    String from = weightConverterFrom.getSelectedItem().toString();
                    String to = weightConverterTo.getSelectedItem().toString();
                    textView.setText(String.valueOf(Weights.valueOf(from).convert(Double.parseDouble(editText.getText().toString()), Weights.valueOf(to))));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private AdapterView.OnItemSelectedListener changeView(EditText editText, TextView textView, Spinner weightConverterFrom, Spinner weightConverterTo) {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (editText.getText().toString().isEmpty()) {
                    textView.setText("");
                } else {
                    String from = weightConverterFrom.getSelectedItem().toString();
                    String to = weightConverterTo.getSelectedItem().toString();
                    textView.setText(String.valueOf(Weights.valueOf(from).convert(Double.parseDouble(editText.getText().toString()), Weights.valueOf(to))));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

}
