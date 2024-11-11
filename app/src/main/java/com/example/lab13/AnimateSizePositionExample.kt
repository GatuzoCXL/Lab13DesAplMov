package com.example.lab13

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimateSizePositionExample() {
    var isExpanded by remember { mutableStateOf(false) }

    val size by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 100.dp,
        label = "size"
    )

    val offset by animateDpAsState(
        targetValue = if (isExpanded) 100.dp else 0.dp,
        label = "offset"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isExpanded = !isExpanded }) {
            Text(text = if (isExpanded) "Contraer" else "Expandir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset, y = offset)
                .background(Color.Blue)
        )
    }
}