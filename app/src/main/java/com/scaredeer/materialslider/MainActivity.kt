package com.scaredeer.materialslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.scaredeer.materialslider.databinding.MainActivityBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    companion object {
        private const val MAX_INDEX = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slider = findViewById<Slider>(R.id.slider)
        slider.addOnChangeListener(
            Slider.OnChangeListener { slider1: Slider?, value: Float, fromUser: Boolean ->
                binding.textView.text = String.format("%.2f", value)
            }
        )
        slider.setLabelFormatter { value: Float -> (MAX_INDEX * value).roundToInt().toString() }

        binding.buttonCenter.setOnClickListener { slider.value = 0.5f }
        binding.buttonLeft.setOnClickListener {
            slider.value = 0f.coerceAtLeast(slider.value - 0.1f)
        }
        binding.buttonRight.setOnClickListener {
            slider.value = (slider.value + 0.1f).coerceAtMost(1.0f)
        }

        binding.toolbar.inflateMenu(R.menu.toolbar)
    }
}