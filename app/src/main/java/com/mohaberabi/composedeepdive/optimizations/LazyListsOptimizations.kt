package com.mohaberabi.composedeepdive.optimizations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ListItem(
    val id: Int,
    val title: String,
    val body: String,
)

@Composable
fun LazyListItemsOptimizationsScreen(modifier: Modifier = Modifier) {


    var list by remember {

        mutableStateOf((1..100).map { ListItem(id = it, "item ${it}", "body ${it}") })
    }


    Scaffold {

            padding ->
        LazyColumn(
            modifier = modifier
                .padding(padding)
                .padding(16.dp),
        ) {
            items(
                list, key = { it.id },
            ) { item ->

                Card {
                    Column {
                        Text(
                            text = item.id.toString(),
                        )
                        Text(
                            text = item.title,
                        )
                        Text(
                            text = item.body,
                        )
                    }
                }
            }
        }
    }
}