package com.sisk.appoint.ui

import androidx.lifecycle.ViewModel
import com.sisk.appoint.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppointViewModel: ViewModel() {
    //app state Location

    suspend fun searchLocation(query: String): List<Location> = listOf(
        Location(name = "Mbabane, Government hospital", latitude = 0.0, longitude = 0.0),
        Location(name = "Manzini, hospital", latitude = 0.0, longitude = 0.0),
        Location(name = "Pigs Peak, hospital", latitude = 0.0, longitude = 0.0),
        Location(name = "Nhlangano, hospital", latitude = 0.0, longitude = 0.0),
        Location(name = "Siteki, hospital", latitude = 0.0, longitude = 0.0)
    )

    private val _uiState = MutableStateFlow(AppointUiState())
    val uiState: StateFlow<AppointUiState> = _uiState.asStateFlow()

    fun updateLocation(location: Location){
        _uiState.update {
            it.copy(location = location)
        }
    }
}
