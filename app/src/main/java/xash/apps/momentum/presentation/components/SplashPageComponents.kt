package xash.apps.momentum.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xash.apps.momentum.ui.theme.blue
import xash.apps.momentum.ui.theme.peanut

@Composable
fun AutoAnimatedLinearProgress() {
    var progress by remember { mutableFloatStateOf(0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 3000) // 1 sec animation
    )

    // Start animation when composable enters composition
    LaunchedEffect(Unit) {
        progress = 0.7f
    }

    LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp),
        color = peanut
    )
}

@Composable
fun AutoAnimatedLinearProgress2() {
    var progress by remember { mutableFloatStateOf(0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 3000) // 1 sec animation
    )

    // Start animation when composable enters composition
    LaunchedEffect(Unit) {
        progress = 0.6f
    }

    LinearProgressIndicator(
        progress = { animatedProgress },
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp),
        color = blue
    )
}