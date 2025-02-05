package dev.mario.appmonitoreo.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mario.appmonitoreo.component.Alerta
import dev.mario.appmonitoreo.component.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val _alertas = MutableStateFlow<List<Alerta>>(emptyList())
    val alertas: StateFlow<List<Alerta>> = _alertas

    init {
        cargarAlertas()
    }

    private fun cargarAlertas() {
        viewModelScope.launch {
            FirebaseRepository.obtenerAlertas().collectLatest { lista ->
                _alertas.value = lista
            }
        }
    }
}