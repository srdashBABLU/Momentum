package xash.apps.momentum.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.copy
import xash.apps.momentum.R
import xash.apps.momentum.data.manager.UrbanistBold
import xash.apps.momentum.data.manager.UrbanistMedium
import xash.apps.momentum.data.manager.UrbanistRegular
import xash.apps.momentum.presentation.components.AutoAnimatedLinearProgress
import xash.apps.momentum.presentation.components.AutoAnimatedLinearProgress2
import xash.apps.momentum.ui.theme.Pink40
import xash.apps.momentum.ui.theme.Pink80
import xash.apps.momentum.ui.theme.Purple40
import xash.apps.momentum.ui.theme.blue
import xash.apps.momentum.ui.theme.peanut
import xash.apps.momentum.ui.theme.purple

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun HomePage(){
    Scaffold(
        containerColor = Color.Black.copy(0.95f)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 42.dp, bottom = 2.dp).fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier.size(48.dp).border(1.dp, Color.White.copy(0.3f), CircleShape),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(Color.Transparent)
                ) {
                    Row (
                        modifier = Modifier.fillMaxSize().clickable(onClick = {}),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
                Row {
                    Card(
                        modifier = Modifier.size(48.dp).border(1.dp, Color.White.copy(0.3f), CircleShape),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(Color.Transparent)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxSize().clickable(onClick = {}),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.notification_icon),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                    Spacer(Modifier.size(8.dp))
                    Card(
                        modifier = Modifier.size(48.dp).border(1.dp, Color.White.copy(0.3f), CircleShape),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(Color.Transparent)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxSize().clickable(onClick = {}),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Hi, Marie Taylor",
                color = Color.White,
                style = TextStyle(
                    fontFamily = UrbanistRegular,
                    fontSize = 26.sp
                )
            )
            Spacer(Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth().height(140.dp),
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
                            strokeWidth = 9.dp,
                            modifier = Modifier.size(96.dp)
                        )

                        Text(
                            text = "${(animatedProgress * 100).toInt()}%",
                            color = Color.White,
                            style = TextStyle(
                                fontFamily = UrbanistMedium,
                                fontSize = 22.sp
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
                            color = Color.White.copy(0.5f)
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
            Spacer(Modifier.height(12.dp))
            Row (
                modifier = Modifier.fillMaxWidth().height(120.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Card(
                    modifier = Modifier.height(120.dp).weight(1f),
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
                                    modifier = Modifier.fillMaxSize().clickable(onClick = {}),
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
                Spacer(Modifier.width(12.dp))
                Card(
                    modifier = Modifier.height(120.dp).weight(1f),
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
                                    modifier = Modifier.fillMaxSize().clickable(onClick = {}),
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
            Spacer(Modifier.height(18.dp))
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "My Task",
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = UrbanistRegular,
                        fontSize = 22.sp
                    )
                )
                Text(
                    text = "See all",
                    color = Purple40,
                    style = TextStyle(
                        fontFamily = UrbanistRegular,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.clickable(onClick = {})
                )
            }

            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth().height(190.dp),
                    colors = CardDefaults.cardColors(Pink80),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Column(
                        Modifier.fillMaxSize().padding(start = 14.dp, end = 12.dp, top = 4.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row (
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column {
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = "Food App UX Research",
                                    color = Color.White,
                                    style = TextStyle(
                                        fontFamily = UrbanistMedium,
                                        fontSize = 18.sp
                                    )
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = "Today 10:30am - 12:45pm",
                                    color = Color.White.copy(0.8f),
                                    style = TextStyle(
                                        fontFamily = UrbanistMedium,
                                        fontSize = 12.sp
                                    )
                                )
                            }

                            Card(
                                modifier = Modifier.size(48.dp).offset(x = 6.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                Row (
                                    modifier = Modifier.fillMaxSize().clickable(onClick = {}),
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
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = Color.White.copy(0.5f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Text(
                                text = "Identify common use cases and scenarios for ordering food.",
                                color = Color.White,
                                style = TextStyle(
                                    fontFamily = UrbanistMedium,
                                    fontSize = 16.sp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row (
                            modifier = Modifier.fillMaxWidth().padding(start = 4.dp, bottom = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Row (
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(36.dp)
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = "Done",
                                    color = Color.White,
                                    style = TextStyle(
                                        fontFamily = UrbanistBold,
                                        fontSize = 18.sp
                                    )
                                )
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(14.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().height(190.dp),
                    colors = CardDefaults.cardColors(purple),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Column(
                        Modifier.fillMaxSize().padding(start = 14.dp, end = 12.dp, top = 4.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row (
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column {
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = "Food App WireFraming",
                                    color = Color.White,
                                    style = TextStyle(
                                        fontFamily = UrbanistMedium,
                                        fontSize = 18.sp
                                    )
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = "Today 10:30am - 12:45pm",
                                    color = Color.White.copy(0.8f),
                                    style = TextStyle(
                                        fontFamily = UrbanistMedium,
                                        fontSize = 12.sp
                                    )
                                )
                            }

                            Card(
                                modifier = Modifier.size(48.dp).offset(x = 6.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                Row (
                                    modifier = Modifier.fillMaxSize().clickable(onClick = {}),
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
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = Color.White.copy(0.5f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Text(
                                text = "Identify common use cases and scenarios for ordering food.",
                                color = Color.White,
                                style = TextStyle(
                                    fontFamily = UrbanistMedium,
                                    fontSize = 16.sp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row (
                            modifier = Modifier.fillMaxWidth().padding(start = 4.dp, bottom = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Row (
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(36.dp)
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = "Done",
                                    color = Color.White,
                                    style = TextStyle(
                                        fontFamily = UrbanistBold,
                                        fontSize = 18.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }


        }
    }
}