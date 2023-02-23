package com.sisk.appoint.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisk.appoint.data.UserRepository
import com.sisk.appoint.model.CreateProfileRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileRegistrationViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

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

    fun createProfile(callback: (Boolean) -> Unit){
        //TODO validation
        val request = CreateProfileRequest(
            name = _profileState.value.name,
            surname = _profileState.value.surname,
            phone = _profileState.value.cell,
            gender = _profileState.value.gender.uppercase(),
            dob = _profileState.value.dob
        )
        _profileState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            userRepository.createProfile(request)
                .catch { error ->
                    Log.d("mama", "error is ${error.message}")
                    // TODO HANDLE ERROR
                    _profileState.update {
                        it.copy(loading = false)
                    }
                    callback(false)
                }
                .collect{
                    _profileState.update { state ->
                        state.copy(loading = false)
                    }
                    callback(true)
                }
        }

    }
}