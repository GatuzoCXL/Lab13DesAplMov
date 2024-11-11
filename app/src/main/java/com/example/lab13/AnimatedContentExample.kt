package com.example.lab13

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed class ContentState {
    object Loading : ContentState()
    object Content : ContentState()
    object Error : ContentState()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample() {
    var currentState by remember { mutableStateOf<ContentState>(ContentState.Loading) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Button(onClick = {
            currentState = when (currentState) {
                is ContentState.Loading -> ContentState.Content
                is ContentState.Content -> ContentState.Error
                is ContentState.Error -> ContentState.Loading
            }
        }) {
            Text("Cambiar Estado")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis = 500)) with
                        fadeOut(animationSpec = tween(durationMillis = 500))
            }
        ) { state ->
            when (state) {
                ContentState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                }
                ContentState.Content -> {
                    Card(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "¡Contenido cargado exitosamente!",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                ContentState.Error -> {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(
                            text = "¡Error al cargar el contenido!",
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
            }
        }
    }
}