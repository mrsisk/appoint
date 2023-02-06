package com.sisk.appoint.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisk.appoint.data.AppointRepository
import com.sisk.appoint.model.AppointDate
import com.sisk.appoint.model.Location
import com.sisk.appoint.model.Period
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(private val appointRepository: AppointRepository): ViewModel() {
    //app state Location


    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            appointRepository.suggestions.collect { suggestions ->
                _uiState.update {
                    it.copy(locationSuggestions = suggestions)
                }
            }
            appointRepository.workingDays.collect{days ->
                _uiState.update {
                    it.copy(days = days, appointDate = days.first())
                }
            }

            appointRepository.workingPeriods.collect{periods ->
                val morning = periods["Morning"] ?: emptyList()
                val afternoon = periods["Afternoon"] ?: emptyList()
                _uiState.update {
                    it.copy(morningPeriods = morning, afternoonPeriods = afternoon, appointPeriod = morning.first())
                }
            }
        }
    }

    suspend fun searchLocation(query: String): List<Location>{
       return appointRepository.findLocation(query)
    }
    fun updateLocation(location: Location){
        _uiState.update {
            it.copy(location = location)
        }
    }

    fun updateAppointDate(appointDate: AppointDate){
        _uiState.update {
            it.copy(appointDate = appointDate)
        }
    }
    fun updateAppointPeriod(period: Period){
        _uiState.update {
            it.copy(appointPeriod = period)
        }
    }

}

