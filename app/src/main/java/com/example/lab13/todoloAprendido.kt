package com.example.lab13

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun todoloAprendido() {
    var isDarkMode by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    var isButtonVisible by remember { mutableStateOf(true) }

    val size by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 100.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val color by animateColorAsState(
        targetValue = if (isExpanded) Color.Magenta else Color.Cyan,
        animationSpec = tween(durationMillis = 500)
    )

    val offset by animateDpAsState(
        targetValue = if (isButtonVisible) 0.dp else 200.dp,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color.DarkGray else Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .size(size)
                .background(color)
                .clickable { isExpanded = !isExpanded }
        )

        AnimatedVisibility(
            visible = isButtonVisible,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally()
        ) {
            Button(
                onClick = { isButtonVisible = !isButtonVisible },
                modifier = Modifier.offset(x = offset)
            ) {
                Text("¡Desaparéceme!")
            }
        }

        AnimatedContent(
            targetState = isDarkMode,
            transitionSpec = {
                fadeIn(animationSpec = tween(600)) with
                        fadeOut(animationSpec = tween(200))
            }
        ) { darkMode ->
            Button(onClick = { isDarkMode = !isDarkMode }) {
                Text(if (darkMode) "Cambiar a Modo Claro" else "Cambiar a Modo Oscuro")
            }
        }
    }
}