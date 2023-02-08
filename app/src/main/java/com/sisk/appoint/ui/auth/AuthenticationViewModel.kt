package com.sisk.appoint.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(): ViewModel() {

    private val _authState = MutableStateFlow(AuthenticationState())
    val authState: StateFlow<AuthenticationState> = _authState.asStateFlow()


    fun setAuthMode(mode: AuthenticationMode){
        _authState.update {
            it.copy(authenticationMode = mode)
        }
    }

    fun authenticate(callBack: ()-> Unit) {
        when(_authState.value.authenticationMode){
            AuthenticationMode.SIGN_UP -> register(callBack)
            AuthenticationMode.SIGN_IN -> register(callBack)
        }
    }

   private fun register(callBack: () -> Unit){
        _authState.update {
            it.copy(loading = true)
        }

      viewModelScope.launch {

            delay(3000L)
            _authState.update {
                it.copy(loading = false)

            }
          callBack()
        }


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

