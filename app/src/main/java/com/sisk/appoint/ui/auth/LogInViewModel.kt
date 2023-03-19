package com.sisk.appoint.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisk.appoint.data.AuthRepository
import com.sisk.appoint.data.TokenRepository
import com.sisk.appoint.model.AuthRequest
import com.sisk.appoint.model.GenericError
import com.sisk.appoint.model.LogInResponse
import com.sisk.appoint.network.AppointResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
): ViewModel(){

    private val _authState = MutableStateFlow(AuthenticationState(authenticationMode = AuthenticationMode.SIGN_IN))
    val authState: StateFlow<AuthenticationState> = _authState.asStateFlow()

    val channel = Channel<String>(Channel.CONFLATED)

    fun authenticate(callBack: (Boolean)-> Unit){
        _authState.update {
            it.copy(loading = true)
        }
        val email = _authState.value.email ?: return
        val password = _authState.value.password ?: return
        viewModelScope.launch {
            val result = authRepository.authenticate(AuthRequest(email = email.trim(), password = password))
            result.collect{response ->
                when(response){
                    is AppointResponse.Error -> {
                       onLoginFailure(response.message)
                        callBack(false)
                    }
                    is AppointResponse.Success -> {
                        onAuthenticationSuccess(response.result)
                        callBack(true)
                    }
                }
            }

        }

    }


    private suspend fun onAuthenticationSuccess(result: LogInResponse) {
        tokenRepository.saveToken(result.access_token)
        _authState.update { it.copy(loading = false) }

    }

    private fun onLoginFailure(error: GenericError?){
        if (error != null){
            channel.trySend(error.message ?: "Failed to login")
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