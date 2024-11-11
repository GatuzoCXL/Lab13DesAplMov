package com.example.lab13

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
fun AnimateColorExample() {
    var isYellow by remember { mutableStateOf(true) }
    val color by animateColorAsState(
        targetValue = if (isYellow) Color.Yellow else Color(0xFFFFA500),
        animationSpec = tween(durationMillis = 1000)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isYellow = !isYellow }) {
            Text(text = if (isYellow) "Cambiar a Naranja" else "Cambiar a Amarillo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color)
        )
    }
}