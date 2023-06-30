package com.sisk.appoint.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisk.appoint.data.AuthRepository
import com.sisk.appoint.model.GenericError
import com.sisk.appoint.model.RegistrationRequest
import com.sisk.appoint.network.AppointResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    private val _authState = MutableStateFlow(RegistrationState())
    val authState: StateFlow<RegistrationState> = _authState.asStateFlow()

    val channel = Channel<String>(Channel.Factory.CONFLATED)

   fun register(callBack: (Boolean)-> Unit){
        _authState.update {
            it.copy(loading = true)
        }
       val email = _authState.value.email ?: return
       val password = _authState.value.password ?: return
       val firstName  = _authState.value.firstName ?: return
       val lastName= _authState.value.lastName ?: return
      viewModelScope.launch {

          val result = authRepository.register(RegistrationRequest(
              email = email,
              password = password,
              firstName = firstName,
              lastName = lastName
          ))
          result.collect{response ->
              when(response){
                  is AppointResponse.Error -> {
                      onRegistrationFailure(response.message)
                      callBack(false)
                  }
                  is AppointResponse.Success -> {
                      onRegistrationSuccess(response.result)
                      callBack(true)
                  }
              }
          }

        }

    }


    private fun onRegistrationSuccess(response: String){
        channel.trySend(response)
        _authState.update { it.copy(loading = false) }

    }

    private fun onRegistrationFailure(response: GenericError){
        if (response.message != null){
            channel.trySend(response.message)
        }else{
            channel.trySend("Registration error occurred")
        }

        _authState.update { it.copy(loading = false) }
    }

    fun onEmailChange(email: String){
        _authState.update {
            it.copy(email = email)
        }
    }

    fun onPasswordChange(password: String){
        _authState.update {
            it.copy(password = password)
        }
    }

    fun onFirstNameChange(name: String){
        _authState.update {
            it.copy(firstName = name)
        }
    }

    fun onLastNameChange(lastName: String){
        _authState.update {
            it.copy(lastName = lastName)
        }
    }

}

