package com.sisk.appoint.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisk.appoint.data.AppointRepository
import com.sisk.appoint.model.AppointDateTime
import com.sisk.appoint.model.Location
import com.sisk.appoint.utils.toAppointDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(private val appointRepository: AppointRepository): ViewModel() {
    //app state Location


    private val _uiState = MutableStateFlow(BookingUiState(
        dateTime = dateTimeFactory(),
        appointDateTime = dateTimeFactory().first(),

    ))
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            appointRepository.suggestions.collect { suggestions ->
                _uiState.update {
                    it.copy(locationSuggestions = suggestions)
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

    fun updateAppointDate(appointDateTime: AppointDateTime){
        _uiState.update {
            it.copy(appointDateTime = appointDateTime)
        }
    }

    private fun dateTimeFactory(): List<AppointDateTime>{
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val dateTime = mutableListOf<AppointDateTime>()
        dateTime.add(calendar.toAppointDate())

        for (num in 1..6){
            calendar.add(Calendar.DATE, 1)

            dateTime.add(calendar.toAppointDate())
        }
        return dateTime
    }
}

