package com.mohaberabi.composedeepdive.optimizations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mohaberabi.composedeepdive.R


@Composable
fun MoveableContent(modifier: Modifier = Modifier) {


    var isRow by remember {
        mutableStateOf(false)
    }

    val moveableRoom = remember {
        movableContentOf {
            RoomImage()
        }
    }
    Column {


        Button(
            onClick = {
                isRow = !isRow
            },
        ) {
            Text(text = "Switch")
        }
        if (isRow) {
            Row {
                moveableRoom()
                Text(text = "My Living Room")
            }
        } else {
            Column {
                moveableRoom()
                Text(text = "My Living Room")
            }
        }

    }
}


@Composable
fun RoomImage(modifier: Modifier = Modifier) {


    Image(
        modifier = modifier.size(100.dp),
        painter = painterResource(id = R.drawable.living_room),
        contentDescription = "LivingRoom"
    )
}