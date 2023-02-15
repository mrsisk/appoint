package com.sisk.appoint.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisk.appoint.data.AuthRepository
import com.sisk.appoint.model.AuthRequest
import com.sisk.appoint.model.RegistrationError
import com.sisk.appoint.network.AppointResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    private val _authState = MutableStateFlow(AuthenticationState(authenticationMode = AuthenticationMode.SIGN_UP))
    val authState: StateFlow<AuthenticationState> = _authState.asStateFlow()

    val channel = Channel<String>(Channel.Factory.CONFLATED)


   fun register(callBack: (Boolean)-> Unit){
        _authState.update {
            it.copy(loading = true)
        }
       val email = _authState.value.email ?: return
       val password = _authState.value.password ?: return
      viewModelScope.launch {

          val result = authRepository.register(AuthRequest(email = email, password = password))
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

    private fun onRegistrationFailure(response: RegistrationError){
        if (response.email != null){
            channel.trySend(response.email)
        }
        if (response.password != null){
            channel.trySend(response.password)
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

}

