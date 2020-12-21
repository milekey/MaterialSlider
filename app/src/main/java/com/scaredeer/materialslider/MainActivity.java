package com.scaredeer.materialslider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity implements Slider.OnChangeListener {
    private TextView textView;
    private static final int MAX_INDEX = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Slider slider = findViewById(R.id.slider);
        slider.addOnChangeListener(this);
        slider.setLabelFormatter(value -> String.valueOf(Math.round(MAX_INDEX * value)));

        textView = findViewById(R.id.textView);
    }

    @Override
    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
        textView.setText(String.valueOf(value));
    }
}