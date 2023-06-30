package com.sisk.appoint.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisk.appoint.data.UserRepository
import com.sisk.appoint.ui.swith.AuthViewModelState
import com.sisk.appoint.utils.UnAuthorizedException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private val _authState = MutableStateFlow(AuthViewModelState())
    val authState = _authState.map(AuthViewModelState::toAuthState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, _authState.value.toAuthState())

    init {

        connect()
    }

    private fun connect(){

        _authState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {

            userRepository.userinfo()
                .catch { error ->

                    when(error){
                        is SocketTimeoutException -> {
                            _authState.update {
                                it.copy(hasError = true, isLoading = false, error = "Timeout connection error")
                            }
                        }
                        is UnAuthorizedException -> {
                            _authState.update {
                                it.copy(isLoading = false, user = null)
                            }
                        }
                        else -> {
                            _authState.update {
                                it.copy(hasError = true, isLoading = false, error = error.localizedMessage)
                            }
                        }
                    }


                }
                .collect{user ->
                    //   if (user == null)
                    _authState.update {
                        it.copy(isLoading = false, user = user)
                    }
                }
        }
    }
    fun retry(){
        _authState.value = AuthViewModelState()
      connect()
    }
}
