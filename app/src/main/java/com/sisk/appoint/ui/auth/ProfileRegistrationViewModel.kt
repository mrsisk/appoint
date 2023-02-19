package com.sisk.appoint.ui.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ProfileRegistrationViewModel: ViewModel() {

    private val _profileState = MutableStateFlow(ProfileRegistrationState())
    val profileState = _profileState.asStateFlow()


    fun onGenderChange(gender: String){
        _profileState.update {
            it.copy(gender = gender)
        }
    }

    fun onNameChange(name: String){
        _profileState.update {
            it.copy(name = name)
        }
    }

    fun onSurnameChange(surname: String){
        _profileState.update {
            it.copy(surname = surname)
        }
    }

    fun onDobChange(dob: String){
        _profileState.update {
            it.copy(dob = dob)
        }
    }

    fun onCellChange(cell: String){
        _profileState.update {
            it.copy(cell = cell)
        }
    }
}