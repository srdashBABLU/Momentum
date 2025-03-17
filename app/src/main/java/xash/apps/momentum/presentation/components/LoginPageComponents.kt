package xash.apps.momentum.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import xash.apps.momentum.data.manager.UrbanistMedium
import xash.apps.momentum.ui.theme.blue
import xash.apps.momentum.ui.theme.peanut
import xash.apps.momentum.ui.theme.purple

@Composable
fun FloatingCard(
    baseOffsetY: androidx.compose.ui.unit.Dp,
    baseOffsetX: androidx.compose.ui.unit.Dp,
    baseRotation: Float,
    width: androidx.compose.ui.unit.Dp,
    height: androidx.compose.ui.unit.Dp,
    color: Color,
    animationDelay: Int
) {
    // Create infinite transition for continuous animation
    val infiniteTransition = rememberInfiniteTransition(label = "floating")

    // Animated values for floating effect
    val yOffset by infiniteTransition.animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "yOffset"
    )

    val xOffset by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "xOffset"
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = baseRotation - 2f,
        targetValue = baseRotation + 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )

    // Animated scale for pulsing effect
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    // Add a shimmer effect
    val shimmerTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerAlpha by shimmerTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmerAlpha"
    )

    // Delay the animation start for each card
    var animationStarted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(animationDelay.toLong())
        animationStarted = true
    }

    // Springs for initial appearance
    val appearanceScale = remember { Animatable(0.5f) }
    val appearanceAlpha = remember { Animatable(0f) }

    LaunchedEffect(animationStarted) {
        if (animationStarted) {
            appearanceScale.animateTo(
                targetValue = 0.9f,
                animationSpec = spring(
                    dampingRatio = 0.6f,
                    stiffness = 100f
                )
            )
            appearanceAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 800)
            )
        }
    }

    // Add a glowing effect
    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Card(
        modifier = Modifier
            .width(width)
            .height(height)
            .offset(
                y = baseOffsetY + yOffset.dp,
                x = baseOffsetX + xOffset.dp
            )
            .rotate(rotation)
            .scale(if (animationStarted) scale * appearanceScale.value else 0.5f)
            .graphicsLayer {
                alpha = appearanceAlpha.value
                shadowElevation = 8f + (glowIntensity * 8f)
            }
        ,
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = shimmerAlpha)
        ),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
            pressedElevation = 6.dp
        )
    ) {
        // Inner content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            color.copy(alpha = 0.8f + glowIntensity),
                            color.copy(alpha = 0.6f)
                        ),
                        radius = 1.5f
                    )
                )
        )
    }
}

@Composable
fun AnimatedDivider() {
    var progress by remember { mutableFloatStateOf(0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1500)
    )

    LaunchedEffect(Unit) {
        progress = 1f
    }

    Box(
        modifier = Modifier
            .width(100.dp * animatedProgress)
            .height(3.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(purple, blue, peanut)
                ),
                shape = CircleShape
            )
    )
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "buttonGlow")
    val glowIntensity by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowIntensity"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp).clip(RoundedCornerShape(28.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
            pressedElevation = 6.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            purple.copy(alpha = 0.8f + glowIntensity),
                            blue.copy(alpha = 0.8f + glowIntensity)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                style = TextStyle(
                    fontFamily = UrbanistMedium,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}