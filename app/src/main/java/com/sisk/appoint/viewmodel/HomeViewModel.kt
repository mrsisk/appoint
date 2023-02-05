package com.sisk.appoint.viewmodel

import androidx.lifecycle.ViewModel
import com.sisk.appoint.R
import com.sisk.appoint.model.Category
import com.sisk.appoint.model.Info
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AppointAppState(
        categories = categories(),
        info = Info(title = "Tuberculosis", description = "Tuberculosis (TB) is caused by a bacterium called Mycobacterium tuberculosis")
    ))
    val uiState: StateFlow<AppointAppState> = _uiState.asStateFlow()

    private fun categories(): List<Category>{
        return listOf(
            Category(title = "Heart specialist", image = R.drawable.heart),
            Category(title = "Dentist", image = R.drawable.tooth),
            Category(title = "Eyes specialist", image = R.drawable.eye),
            Category(title = "Maternity",image = R.drawable.heart)
        )
    }
}