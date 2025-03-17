package xash.apps.momentum.data.manager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xash.apps.momentum.presentation.ui.LoginPage
import xash.apps.momentum.presentation.ui.SignupPage
import xash.apps.momentum.presentation.ui.SplashPage

@Composable
fun AppNavigation(){
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = "splash"){
        composable("splash") { SplashPage() }
        composable("login") { LoginPage() }
        composable("signup") { SignupPage() }
    }
}