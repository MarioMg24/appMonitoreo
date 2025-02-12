package dev.mario.appmonitoreo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.mario.appmonitoreo.cards.AlertasCriticasCard
import dev.mario.appmonitoreo.component.DashboardViewModel
import dev.mario.appmonitoreo.cards.DiaNivelMasAltoCard
import dev.mario.appmonitoreo.cards.EstadoAlerta
import dev.mario.appmonitoreo.cards.NivelAguaPromedioCard
import dev.mario.appmonitoreo.cards.UmbralesAlertaCard

@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel = viewModel()) {
    val nivelAguaPromedio by viewModel.nivelAguaPromedio.collectAsState()
    val diaNivelMasAlto by viewModel.diaNivelMasAlto.collectAsState()
    val totalAlertasCriticas by viewModel.totalAlertasCriticas.collectAsState()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
                .verticalScroll(scrollState), // Add scroll functionality here
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Panel de Monitoreo",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Llamar al componente UmbralesAlertaCard
            UmbralesAlertaCard()

            Spacer(modifier = Modifier.height(16.dp))

            // Llamar al componente NivelAguaPromedioCard
            NivelAguaPromedioCard(nivelAguaPromedio)

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar el estado de la alerta
            EstadoAlerta(nivelAguaPromedio)

            Spacer(modifier = Modifier.height(16.dp))

            // Llamar al componente DiaNivelMasAltoCard
            DiaNivelMasAltoCard(diaNivelMasAlto)

            Spacer(modifier = Modifier.height(16.dp))

            // Llamar al componente AlertasCriticasCard
            AlertasCriticasCard(totalAlertasCriticas)

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para ir al historial de alertas
            Button(
                onClick = { navController.navigate("historial_alertas") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Historial de Alertas",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Historial de Alertas",
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para cerrar sesión
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Cerrar Sesión",
                        tint = MaterialTheme.colorScheme.onError
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Cerrar Sesión",
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
        }
    }
}