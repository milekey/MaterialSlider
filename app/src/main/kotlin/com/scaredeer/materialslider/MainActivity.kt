package com.scaredeer.materialslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.scaredeer.materialslider.ui.theme.AppTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                PairedSlider()
            }
        }
    }
}

@Composable
fun PairedSlider() {
    var commonValue by rememberSaveable { mutableFloatStateOf(0.5f) }

    val analogueSliderState = rememberSliderState(
        value = commonValue,
        trackRange = 0f..1f
    )
    val discreteSliderState = rememberSliderState(
        value = commonValue,
        trackRange = 0f..1f,
        steps = 9
    )

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    Slider(
                        state = discreteSliderState,
                        onValueChange = {
                            commonValue = it
                            analogueSliderState.value = commonValue
                            discreteSliderState.value = commonValue
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .windowInsetsPadding(
                                WindowInsets.safeGestures.only(WindowInsetsSides.Start)
                            )
                    )
                    IconButton(onClick = {
                        commonValue = 0f.coerceAtLeast(commonValue - 0.1f)
                        analogueSliderState.value = commonValue
                        discreteSliderState.value = commonValue
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back_24),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        commonValue = 0.5f
                        analogueSliderState.value = commonValue
                        discreteSliderState.value = commonValue
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_refresh_24),
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = {
                            commonValue =
                                (commonValue + 0.1f).coerceAtMost(1.0f)
                            analogueSliderState.value = commonValue
                            discreteSliderState.value = commonValue
                        },
                        modifier = Modifier.windowInsetsPadding(
                                WindowInsets.safeGestures.only(WindowInsetsSides.End)
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_forward_24),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = String.format(Locale.JAPAN, "%.2f", commonValue),
                modifier = Modifier
            )
            Slider(
                state = analogueSliderState,
                onValueChange = {
                    commonValue = it
                    analogueSliderState.value = commonValue
                    discreteSliderState.value = commonValue
                },
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.safeGestures.only(WindowInsetsSides.Horizontal)
                )
            )
            Row(modifier = Modifier) {
                Button(
                    onClick = {
                        commonValue = 0f.coerceAtLeast(commonValue - 0.1f)
                        analogueSliderState.value = commonValue
                        discreteSliderState.value = commonValue
                    }
                ) {
                    Text(
                        text = "←",
                        modifier = Modifier
                    )
                }
                Button(
                    onClick = {
                        commonValue = 0.5f
                        analogueSliderState.value = commonValue
                        discreteSliderState.value = commonValue
                    }
                ) {
                    Text(
                        text = "CENTER",
                        modifier = Modifier
                    )
                }
                Button(
                    onClick = {
                        commonValue = (commonValue + 0.1f).coerceAtMost(1.0f)
                        analogueSliderState.value = commonValue
                        discreteSliderState.value = commonValue
                    }
                ) {
                    Text(
                        text = "→",
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PairedSliderPreview() {
    AppTheme {
        PairedSlider()
    }
}