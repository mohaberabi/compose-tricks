package com.mohaberabi.composedeepdive.sideeffects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.flowOf


val flow = flowOf(
    1,
    2,
    1,
)

@Composable
fun SnapShotFlowScreen(

) {
    val state = snapshotFlow { flow }.collectAsStateWithLifecycle(initialValue = 0)
}