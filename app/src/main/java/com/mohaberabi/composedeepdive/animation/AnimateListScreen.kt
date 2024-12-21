package com.mohaberabi.composedeepdive.animation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimateListScreen(modifier: Modifier = Modifier) {


    var items by remember {

        mutableStateOf(

            (1..100).map { "List Item ${it}" }
        )
    }


    Column(
        modifier = modifier.fillMaxSize(),
    ) {


        Button(
            onClick = {
                items = items.shuffled()
            },
        ) {
            Text(text = "Shuffle Items")
        }

        LazyColumn {
            items(
                items,
                key = { it }
            ) { itm ->
                Text(
                    text = itm,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .animateItemPlacement()
                )
            }
        }

    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewListAnimatoinScreen() {

    ComposeDeepDiveTheme {

        AnimateListScreen()
    }
}