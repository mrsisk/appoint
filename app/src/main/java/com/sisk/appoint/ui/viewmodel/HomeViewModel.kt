package com.sisk.appoint.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sisk.appoint.R
import com.sisk.appoint.data.AppointRepository
import com.sisk.appoint.data.UserRepository
import com.sisk.appoint.model.Category
import com.sisk.appoint.model.Info
import com.sisk.appoint.utils.UnAuthorizedException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appointRepository: AppointRepository,
    private val userRepository: UserRepository
): ViewModel() {


    private val _uiState = MutableStateFlow(
        AppointAppState(
        categories = categories(),
        info = Info(title = "Tuberculosis", description = "Tuberculosis (TB) is caused by a bacterium called Mycobacterium tuberculosis")
    )
    )
    val uiState: StateFlow<AppointAppState> = _uiState.asStateFlow()

    init {
       // connect()
    }

    private fun connect(){
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            userRepository.userinfo()
                .catch {error ->
                    when(error){
                        is SocketTimeoutException -> Log.d("mama", "handel timeout")
                        is  UnAuthorizedException -> {
                            _uiState.update {
                                it.copy(user = null, isLoading = false)
                            }
                        }
                    }
                }
                .collect{user ->
                    _uiState.update {
                        it.copy(user = user, isLoading = false)
                    }
                }
        }
    }

    fun checkAuth(){
        _uiState.value = AppointAppState(
            categories = categories(),
            info = Info(title = "Tuberculosis", description = "Tuberculosis (TB) is caused by a bacterium called Mycobacterium tuberculosis")
        )
        connect()
    }
    private fun categories(): List<Category>{
        return listOf(
            Category(title = "Heart specialist", image = R.drawable.heart),
            Category(title = "Dentist", image = R.drawable.tooth),
            Category(title = "Eyes specialist", image = R.drawable.eye),
            Category(title = "Maternity",image = R.drawable.heart)
        )
    }

    fun test(){
        viewModelScope.launch {
            appointRepository.test()
        }

    }
}