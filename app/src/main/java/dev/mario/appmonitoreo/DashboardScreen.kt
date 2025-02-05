package dev.mario.appmonitoreo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.mario.appmonitoreo.component.DashboardViewModel

@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel = viewModel()) {
    val alertas by viewModel.alertas.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Bienvenido al Panel de Monitoreo", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Navegación a otra pantalla */ },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Monitorear Pozos Sépticos")
        }

        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Cerrar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (alertas.isEmpty()) {
            Text(text = "No hay alertas disponibles", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(alertas) { alerta ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Día: ${alerta.dia}", style = MaterialTheme.typography.bodyLarge)
                            Text("Nivel del Agua: ${alerta.nivel_agua_m} m", style = MaterialTheme.typography.bodyLarge)
                            Text("Alerta: ${alerta.alerta}", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}