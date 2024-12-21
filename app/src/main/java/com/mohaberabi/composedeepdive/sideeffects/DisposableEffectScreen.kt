package com.mohaberabi.composedeepdive.sideeffects

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner


@Composable
fun DisposableEffectScreen(modifier: Modifier = Modifier) {


    val owner = LocalLifecycleOwner.current



    DisposableEffect(
        owner.lifecycle,
    ) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> TODO()
                Lifecycle.Event.ON_START -> TODO()
                Lifecycle.Event.ON_RESUME -> TODO()
                Lifecycle.Event.ON_PAUSE -> TODO()
                Lifecycle.Event.ON_STOP -> TODO()
                Lifecycle.Event.ON_DESTROY -> TODO()
                Lifecycle.Event.ON_ANY -> TODO()
            }
        }
        owner.lifecycle.addObserver(observer)
        onDispose {
            owner.lifecycle.removeObserver(observer)
        }
    }
}