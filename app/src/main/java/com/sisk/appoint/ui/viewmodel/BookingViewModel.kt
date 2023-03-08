package com.sisk.appoint.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisk.appoint.data.AppointRepository
import com.sisk.appoint.data.ScheduleRepository
import com.sisk.appoint.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val appointRepository: AppointRepository,
    private val scheduleRepository: ScheduleRepository
): ViewModel() {
    //app state Location


    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            scheduleRepository.getSchedule()
                .catch {
                    Log.d("mama", "booking repo ${it.message}")
                }
                .collect{schedules ->
                    Log.d("mama", "booking repo got $schedules")
                    _uiState.update {
                        it.copy(
                            schedules = schedules.map { wd -> wd.toUiModel() },
                            schedule = schedules.first().toUiModel(),
                            workPeriod = schedules.first().toUiModel().workPeriod[SessionType.MORNING]?.first()
                        )
                    }
                }
        }
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

    fun updateAppointDate(appointDate: WorkDayUi){
        _uiState.update {
            it.copy(schedule = appointDate, workPeriod = appointDate.workPeriod[SessionType.MORNING]?.first())
        }
    }
    fun updateAppointPeriod(period: WorkPeriod){
        _uiState.update {
            it.copy(workPeriod = period)
        }
    }

    fun onAdditionalInfoChange(text: String){
        _uiState.update {
            it.copy(additionalInfo = text)
        }
    }

    fun updateCategory(category: Category){
        _uiState.update {
            it.copy(category = category)
        }
    }

    fun book(callback: (Boolean) -> Unit){
        val start = _uiState.value.workPeriod?.start ?: return
        val end = _uiState.value.workPeriod?.end ?: return
        val description = _uiState.value.additionalInfo
        viewModelScope.launch {
            scheduleRepository.book(BookingRequest(
                start.toString(),
                end.toString(),
                description
            ))
                .catch {
                    Log.d("mama", "failed to book ${it.message}")
                    callback(false)
                }
                .collect{
                    callback(true)
                    Log.d("mama", "booking successful $it")
                }
        }


    }

}

