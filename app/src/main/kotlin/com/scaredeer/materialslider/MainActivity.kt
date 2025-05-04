package com.scaredeer.materialslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemGestures
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
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
                SliderSample()
            }
        }
    }
}

@Composable
fun SliderSample() {
    var sliderPosition by rememberSaveable { mutableFloatStateOf(0.5f) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(
                                start =
                                    WindowInsets.systemGestures.asPaddingValues()
                                        .calculateStartPadding(LocalLayoutDirection.current),
                            )
                    )
                    IconButton(onClick = {
                        sliderPosition = 0f.coerceAtLeast(sliderPosition - 0.1f)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back_24),
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = {
                        sliderPosition = 0.5f
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_refresh_24),
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        onClick = {
                            sliderPosition =
                                (sliderPosition + 0.1f).coerceAtMost(1.0f)
                        },
                        modifier = Modifier
                            .padding(
                                end =
                                    WindowInsets.systemGestures.asPaddingValues()
                                        .calculateEndPadding(LocalLayoutDirection.current)
                            )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_forward_24),
                            contentDescription = ""
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
                text = String.format(Locale.JAPAN, "%.2f", sliderPosition),
                modifier = Modifier
            )
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                modifier = Modifier.padding(
                    start =
                        WindowInsets.systemGestures.asPaddingValues()
                            .calculateStartPadding(LocalLayoutDirection.current),
                    end =
                        WindowInsets.systemGestures.asPaddingValues()
                            .calculateEndPadding(LocalLayoutDirection.current)
                )
            )
            Row(modifier = Modifier) {
                Button(
                    onClick = {
                        sliderPosition = 0f.coerceAtLeast(sliderPosition - 0.1f)
                    }
                ) {
                    Text(
                        text = "←",
                        modifier = Modifier
                    )
                }
                Button(
                    onClick = {
                        sliderPosition = 0.5f
                    }
                ) {
                    Text(
                        text = "CENTER",
                        modifier = Modifier
                    )
                }
                Button(
                    onClick = {
                        sliderPosition = (sliderPosition + 0.1f).coerceAtMost(1.0f)
                    }
                ) {
                    Text(
                        text = "→",
                        modifier = Modifier
                    )
                }
            }
        }
    }}

@Preview(showBackground = true)
@Composable
fun SliderSamplePreview() {
    AppTheme {
        SliderSample()
    }
}