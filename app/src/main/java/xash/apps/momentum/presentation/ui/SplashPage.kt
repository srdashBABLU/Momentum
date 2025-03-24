package xash.apps.momentum.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xash.apps.momentum.R
import xash.apps.momentum.data.manager.UrbanistMedium
import xash.apps.momentum.data.manager.UrbanistRegular
import xash.apps.momentum.presentation.components.AutoAnimatedLinearProgress
import xash.apps.momentum.presentation.components.AutoAnimatedLinearProgress2
import xash.apps.momentum.ui.theme.blue
import xash.apps.momentum.ui.theme.peanut
import xash.apps.momentum.ui.theme.purple

@Preview(showBackground = true)
@Composable
fun SplashPage(){
    Column(
        modifier = Modifier
            .fillMaxSize().background(Color.Black.copy(0.95f))
            .padding(start = 16.dp, end = 16.dp, top = 42.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.iphone_png),
            contentDescription = null,
            modifier = Modifier.size(600.dp).scale(1.9f)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.width(300.dp).height(120.dp).rotate(6f).offset(y = (-70).dp, x = (-10).dp),
            colors = CardDefaults.cardColors(purple),
            shape = RoundedCornerShape(22.dp)
        ) {
            var progress by remember { mutableFloatStateOf(0f) }

            // Animate the progress from 0.0f to 0.7f
            val animatedProgress by animateFloatAsState(
                targetValue = progress,
                animationSpec = tween(durationMillis = 3000) // 1 sec smooth animation
            )

            LaunchedEffect(Unit) {
                progress = 0.7f // Start animation when composable is launched
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = {
                            animatedProgress // 70% progress
                        },
                        color = Color.White,
                        strokeWidth = 8.dp,
                        modifier = Modifier.size(78.dp)
                    )

                    Text(
                        text = "${(animatedProgress * 100).toInt()}%",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = UrbanistMedium,
                            fontSize = 20.sp
                        ),
                        fontWeight = FontWeight.Bold,
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Progress\nToday Task",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = UrbanistMedium,
                            fontSize = 26.sp
                        ),
                        textAlign = TextAlign.Start,
                        softWrap = true
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    HorizontalDivider(
                        modifier = Modifier.width(120.dp),
                        thickness = 1.dp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text= "6/10 Task Completed",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = UrbanistRegular,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center,
                        softWrap = true
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight().padding(top = 8.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        Card(
            modifier = Modifier.width(150.dp).height(120.dp).rotate(-6f).offset(y = (-60).dp, x = (-76).dp),
            colors = CardDefaults.cardColors(blue),
            shape = RoundedCornerShape(22.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 6.dp, start = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Today's task",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = UrbanistMedium,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center,
                        softWrap = true
                    )
                    Card(
                        modifier = Modifier.size(48.dp),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.rotate(-45f).size(26.dp)
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 6.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "4/9 Done",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = UrbanistMedium,
                            fontSize = 24.sp
                        ),
                        textAlign = TextAlign.Center,
                        softWrap = true
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.padding(start = 6.dp, end = 12.dp)
                ){
                    AutoAnimatedLinearProgress()
                }
            }
        }
        Card(
            modifier = Modifier.width(150.dp).height(120.dp).rotate(8f).offset(y = (-110).dp, x = (60).dp),
            colors = CardDefaults.cardColors(peanut),
            shape = RoundedCornerShape(22.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 6.dp, start = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "in progress",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = UrbanistMedium,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center,
                        softWrap = true
                    )
                    Card(
                        modifier = Modifier.size(48.dp),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.rotate(-45f).size(26.dp)
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 6.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "2/5 Tasks",
                        color = Color.White,
                        style = TextStyle(
                            fontFamily = UrbanistMedium,
                            fontSize = 24.sp
                        ),
                        textAlign = TextAlign.Center,
                        softWrap = true
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.padding(start = 6.dp, end = 12.dp)
                ){
                    AutoAnimatedLinearProgress2()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize().blur(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().background(Color.Black).height(350.dp)
        )
    }
    Column (
        modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 42.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Text(
            text = "Manage & Organise your daily tasks easily.",
            color = Color.White,
            style = TextStyle(
                fontFamily = UrbanistMedium,
                fontSize = 28.sp
            ),
            textAlign = TextAlign.Center,
            softWrap = true
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Start today and enjoy uninterrupted services until your goal achievement !",
            color = Color.LightGray,
            style = TextStyle(
                fontFamily = UrbanistMedium,
                fontSize = 16.sp
            ),
            textAlign = TextAlign.Center,
            softWrap = true,
            modifier = Modifier.padding(start = 6.dp, end = 6.dp)
        )
        Spacer(Modifier.height(26.dp))
        Card(
            modifier = Modifier.fillMaxWidth().height(58.dp).padding(start = 8.dp, end = 8.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(Color.White.copy(0.1f))
        ) {
            Row (
                modifier = Modifier.fillMaxSize().clickable(onClick = {}),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Let's get Started...",
                    color = Color.LightGray,
                    style = TextStyle(
                        fontFamily = UrbanistMedium,
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

