package com.scaredeer.materialslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.scaredeer.materialslider.databinding.MainActivityBinding
import java.util.Locale
import kotlin.math.roundToInt

private const val MAX_INDEX = 100

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.slider.addOnChangeListener(
            Slider.OnChangeListener { slider1: Slider?, value: Float, fromUser: Boolean ->
                binding.textView.text = String.format(Locale.JAPAN, "%.2f", value)
            }
        )
        binding.slider.setLabelFormatter {
            value: Float -> (MAX_INDEX * value).roundToInt().toString()
        }

        binding.buttonCenter.setOnClickListener { binding.slider.value = 0.5f }
        binding.buttonLeft.setOnClickListener {
            binding.slider.value = 0f.coerceAtLeast(binding.slider.value - 0.1f)
        }
        binding.buttonRight.setOnClickListener {
            binding.slider.value = (binding.slider.value + 0.1f).coerceAtMost(1.0f)
        }

        binding.toolbar.inflateMenu(R.menu.toolbar)
    }
}