package com.scaredeer.materialslider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private static final int MAX_INDEX = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        textView = findViewById(R.id.textView);

        Slider slider = findViewById(R.id.slider);
        slider.addOnChangeListener((slider1, value, fromUser) -> textView.setText(String.format("%.2f", value)));
        slider.setLabelFormatter(value -> String.valueOf(Math.round(MAX_INDEX * value)));

        Button buttonCenter = findViewById(R.id.buttonCenter);
        buttonCenter.setOnClickListener(view -> slider.setValue(0.5f));
        Button buttonLeft = findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(view -> slider.setValue(Math.max(0, slider.getValue() - 0.1f)));
        Button buttonRight = findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(view -> slider.setValue(Math.min(slider.getValue() + 0.1f, 1.0f)));
    }
}