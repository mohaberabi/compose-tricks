package com.mohaberabi.composedeepdive.optimizations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key


data class Section(
    val id: Int,
    val header: String,
    val description: String,
)


@Composable
fun CustomLayoutKeysScreen(modifier: Modifier = Modifier) {


    var sections by remember {
        mutableStateOf((1..3).map {
            Section(
                id = it,
                "Section $it Header",
                description = "Section $it Description"
            )
        })
    }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        for (section in sections) {
            key(section.id) {
                Column {
                    Text(
                        text = section.header,
                        modifier = Modifier
                    )
                    Text(text = section.description)

                }
            }

        }

    }
}