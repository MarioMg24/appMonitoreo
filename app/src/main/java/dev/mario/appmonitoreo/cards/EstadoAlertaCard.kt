package dev.mario.appmonitoreo.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
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
fun EstadoAlerta(nivelAguaPromedio: Double?) {
    if (nivelAguaPromedio != null) {
        val (estado, color, icon) = when {
            nivelAguaPromedio < UmbralesAlerta.BAJO -> Triple("Bajo", Color.Green, Icons.Filled.CheckCircle)
            nivelAguaPromedio < UmbralesAlerta.MODERADO -> Triple("Moderado", Color.Yellow, Icons.Filled.Warning)
            nivelAguaPromedio < UmbralesAlerta.ALTO -> Triple("Alto", Color(0xFFFFA500), Icons.Filled.Error)
            else -> Triple("Crítico", Color.Red, Icons.Filled.SentimentVeryDissatisfied)
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = estado,
                    modifier = Modifier.size(48.dp),
                    tint = color
                )
                Text(
                    text = "Estado actual:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = estado,
                    style = MaterialTheme.typography.titleLarge,
                    color = color,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}