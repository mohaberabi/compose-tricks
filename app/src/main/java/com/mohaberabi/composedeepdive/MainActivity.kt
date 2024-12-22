package com.mohaberabi.composedeepdive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mohaberabi.composedeepdive.animation.LayoutAnimationScreen
import com.mohaberabi.composedeepdive.interaction.DragabbleScreen
import com.mohaberabi.composedeepdive.interaction.FocusScreen
import com.mohaberabi.composedeepdive.layouts.MinMapItem
import com.mohaberabi.composedeepdive.layouts.MindMappingScreen
import com.mohaberabi.composedeepdive.layouts.SubComposePagedRow
import com.mohaberabi.composedeepdive.optimizations.CounterScreenRoot
import com.mohaberabi.composedeepdive.sideeffects.RememberUpdatedStateScreen
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDeepDiveTheme {
                Scaffold { padding ->
                    CounterScreenRoot(
                        modifier = Modifier
                            .padding(padding)
                            .padding(20.dp)
                    )
                }
            }
        }
    }
}

