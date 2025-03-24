package xash.apps.momentum.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun HomePage(){
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp,end = 16.dp, top = 16.dp, bottom = 16.dp),
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
                                imageVector = Icons.Default.Search,
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
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            Text(
                text = "Hi, Marie Taylor",
                color = Color.White,
            )
        }
    }
}