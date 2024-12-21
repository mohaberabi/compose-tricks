package com.mohaberabi.composedeepdive.interaction

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun InteractionScreen(modifier: Modifier = Modifier) {
    val interactionResource = remember {
        MutableInteractionSource()
    }

    val isPressed by interactionResource.collectIsPressedAsState()

    val hovered by interactionResource.collectIsHoveredAsState()
    val focused by interactionResource.collectIsFocusedAsState()
    val dragged by interactionResource.collectIsDraggedAsState()

    LaunchedEffect(
        hovered,
        focused,
        isPressed,
        dragged,
    ) {
        if (isPressed) {
            println("isPressed")
        }
        if (hovered) {
            println("hovered")
        }
        if (focused) {
            println("focused")
        }
        if (dragged) {
            println("dragged")
        }
    }
    Scaffold(
        modifier = Modifier.clickable(
            interactionResource,
            null,
        ) {}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
        ) {


            Button(
                onClick = {
                    println(" Button Pressed")
                },
            ) {
                Text(text = "Press Me")
            }

        }
    }
}