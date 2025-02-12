// DashboardViewModel.kt
package dev.mario.appmonitoreo.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val _todasLasAlertas = MutableStateFlow<List<Alerta>>(emptyList())
    val listaAlertas: StateFlow<List<Alerta>> = _todasLasAlertas

    private val _nivelAguaPromedio = MutableStateFlow<Double?>(null)
    val nivelAguaPromedio: StateFlow<Double?> = _nivelAguaPromedio

    // Nuevo StateFlow para el día con nivel más alto
    private val _diaNivelMasAlto = MutableStateFlow<Pair<String, Double>?>(null)
    val diaNivelMasAlto: StateFlow<Pair<String, Double>?> = _diaNivelMasAlto

    // Nuevo StateFlow para el número de alertas críticas
    private val _totalAlertasCriticas = MutableStateFlow<Int>(0)
    val totalAlertasCriticas: StateFlow<Int> = _totalAlertasCriticas

    init {
        cargarTodasLasAlertas()
    }

    private fun cargarTodasLasAlertas() {
        viewModelScope.launch {
            FirebaseRepository.obtenerTodasLasAlertas().collect { alertas ->
                _todasLasAlertas.value = alertas
                calcularEstadisticas(alertas) // Calcular el promedio después de cargar las alertas
            }
        }
    }
    private fun calcularEstadisticas(alertas: List<Alerta>) {
        if (alertas.isNotEmpty()) {
            // Calcular promedio
            val promedio = alertas.map { it.nivel_agua_m }.average()
            _nivelAguaPromedio.value = promedio

            // Encontrar el día con el nivel más alto
            val alertaMasAlta = alertas.maxByOrNull { it.nivel_agua_m }
            alertaMasAlta?.let {
                _diaNivelMasAlto.value = Pair(it.dia.toString(), it.nivel_agua_m)
            }
            // Contar el número de alertas críticas
            val totalCriticas = alertas.count { it.alerta == "Crítico" }
            _totalAlertasCriticas.value = totalCriticas
        }else {
            _nivelAguaPromedio.value = null
            _diaNivelMasAlto.value = null
            _totalAlertasCriticas.value = 0 // Reiniciar el contador si no hay alertas
        }
    }
}
