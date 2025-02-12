package dev.mario.appmonitoreo.cards

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.mario.appmonitoreo.component.UmbralesAlerta

@Composable
fun UmbralesAlertaCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Umbrales de Alerta",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Umbrales de Alerta",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Bajo: ${UmbralesAlerta.BAJO} m",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Moderado: ${UmbralesAlerta.MODERADO} m",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Alto: ${UmbralesAlerta.ALTO} m",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Crítico: ${UmbralesAlerta.CRITICO} m",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red
            )
        }
    }
}