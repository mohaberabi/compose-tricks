package com.mohaberabi.composedeepdive.animation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mohaberabi.composedeepdive.ui.theme.ComposeDeepDiveTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mohaberabi.composedeepdive.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementTransitionScreen(modifier: Modifier = Modifier) {
    var expanded by remember {

        mutableStateOf(false)
    }

    SharedTransitionLayout(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {


            AnimatedVisibility(visible = !expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    ListItem(
                        animateScope = this@AnimatedVisibility,
                        modifier = Modifier,
                        onClick = {
                            expanded = !expanded
                        },
                    )
                }

            }
            AnimatedVisibility(visible = expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    ColumnListItem(
                        animateScope = this@AnimatedVisibility,
                        onClick = {
                            expanded = !expanded
                        },
                    )
                }
            }


        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListItem(
    modifier: Modifier = Modifier,
    animateScope: AnimatedVisibilityScope,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.living_room),
            contentDescription = "image",
            modifier = Modifier
                .size(100.dp)
                .sharedElement(
                    state = rememberSharedContentState(key = "img"),
                    animatedVisibilityScope = animateScope
                ).clickable { onClick() },

            )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Hello mohab the best loser in the world",
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ColumnListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    animateScope: AnimatedVisibilityScope,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.living_room),
            contentDescription = "image",
            modifier = Modifier
                .size(300.dp)
                .sharedElement(
                    rememberSharedContentState(key = "img"),
                    animatedVisibilityScope = animateScope
                )
                .clickable { onClick() },
        )
        Text(
            text = "Hello mohab the best loser in the world",
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewSharedElementTransitionScreen() {
    ComposeDeepDiveTheme {
        SharedElementTransitionScreen()
    }
}