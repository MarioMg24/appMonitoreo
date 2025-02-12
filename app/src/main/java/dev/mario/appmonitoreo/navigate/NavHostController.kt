package dev.mario.appmonitoreo.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import dev.mario.appmonitoreo.screens.DashboardScreen
import dev.mario.appmonitoreo.screens.HomeScreen
import dev.mario.appmonitoreo.Login.RegisterScreen
import dev.mario.appmonitoreo.screens.HistorialAlertasScreen

@Composable
fun NavHostController(navController: NavHostController, auth: FirebaseAuth) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, auth) }
        composable("register") { RegisterScreen(navController, auth) }
        composable("dashboard") { DashboardScreen(navController) }
        composable("historial_alertas") {HistorialAlertasScreen(navController)
        }
    }
}