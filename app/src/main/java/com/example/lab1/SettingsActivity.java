package com.example.lab1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SettingsActivity extends AppCompatActivity {

    private static final String KEY = "USER";
    private UserDto user;

    private final Calendar calendar = Calendar.getInstance();
    private EditText nameEdit;
    private EditText surnameEdit;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private Button date;
    private Button save;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        if (savedInstanceState != null) {
            user = savedInstanceState.getParcelable(KEY);
        }


        save = findViewById(R.id.saveButton);
        nameEdit = this.findViewById(R.id.nameEdit);
        surnameEdit = this.findViewById(R.id.surnameEdit);
        date = this.findViewById(R.id.datePicker);
        genderRadioGroup = this.findViewById(R.id.radioGender);

        date.setOnClickListener(v -> {
            setDate();
        });

        save.setOnClickListener(v -> {

            int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = genderRadioGroup.findViewById(selectedRadioButtonId);

            UserDto user = new UserDto(nameEdit.getText().toString(), surnameEdit.getText().toString(), new Date(), radioButton.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("USER", user);
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY, user);

    }

    public void setDate() {
        new DatePickerDialog(SettingsActivity.this, d,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            Date iDate = calendar.getTime();
            date.setText(sdf.format(iDate));
        }
    };

}
