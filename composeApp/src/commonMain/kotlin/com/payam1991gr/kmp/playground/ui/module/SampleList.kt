package com.payam1991gr.kmp.playground.ui.module

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.PersistentList

@Composable
fun <Sample> SampleList(
    samples: PersistentList<Sample>,
    nameOf: @Composable (Sample) -> String,
    onClick: (Sample) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isDark = isSystemInDarkTheme()
    LazyVerticalGrid(
        GridCells.Adaptive(250.dp),
        contentPadding = PaddingValues(1.dp),
        modifier = modifier,
    ) {
        items(samples) { sample ->
            Button(
                { onClick(sample) },
                Modifier.padding(1.dp)
                    .height(80.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                if (isDark) Color.DarkGray else Color.LightGray,
                                Color.Gray,
                            )
                        ),
                        RectangleShape,
                    ),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    nameOf(sample),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 8.dp),
                )
            }
        }
    }
}
