package xash.apps.momentum.data.local.xpValue

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun XpScreen() {
    val scope = rememberCoroutineScope()
    val xpState by XpRepository.xpFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "XP Points: $xpState", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(onClick = { scope.launch { XpRepository.updateXp(xpState + 10) } }) {
                Text("Increase XP")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { scope.launch { XpRepository.updateXp(xpState - 10) } }) {
                Text("Decrease XP")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { scope.launch { XpRepository.updateXp(0) } }) {
                Text("Reset XP")
            }
        }
    }
}