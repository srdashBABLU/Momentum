package xash.apps.momentum

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch
import xash.apps.momentum.data.local.task_list.TaskDatabase
import xash.apps.momentum.data.local.xpValue.XpDatabase
import xash.apps.momentum.data.local.xpValue.XpRepository
import xash.apps.momentum.presentation.ui.HomePage
import xash.apps.momentum.presentation.ui.SplashPage
import xash.apps.momentum.ui.theme.MomentumTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the database first
        val database = Room.databaseBuilder(
            applicationContext,
            XpDatabase::class.java,
            "xp_db"
        ).build()

        XpRepository.initDatabase(database)  // Ensure db is initialized before accessing it

        lifecycleScope.launch {
            XpRepository.loadXp()
        }

        // Tasks Db initialisation
        val taskDataBase = TaskDatabase.getDatabase(applicationContext)


        setContent {
            MomentumTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  innerPadding ->
                    //TaskScreen(taskDataBase)
                    //CameraApp()
                    //StateDemo()
                    HomePage()
                    //SplashPage()
                }
            }
        }
    }
}
