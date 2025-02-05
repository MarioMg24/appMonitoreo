package dev.mario.appmonitoreo

import android.widget.CheckBox
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import dev.mario.appmonitoreo.Login.RegisterScreen

@Composable
fun NavHostController(navController: NavHostController, auth: FirebaseAuth) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, auth) }
        composable("register") { RegisterScreen(navController, auth) }
        composable("dashboard") { DashboardScreen(navController) }
    }
}