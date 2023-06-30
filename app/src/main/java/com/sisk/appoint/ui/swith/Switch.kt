package com.sisk.appoint.ui.swith

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sisk.appoint.navigation.Graph
import com.sisk.appoint.ui.components.ErrorScreen
import com.sisk.appoint.ui.components.LoadingScreen
import com.sisk.appoint.ui.viewmodel.AuthViewModel

@Composable
fun Switch(
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val authState by authViewModel.authState.collectAsState()


    when(authState){
        is AuthState.Authenticated -> {
            if (authState.isLoading) LoadingScreen()
            LaunchedEffect(key1 = authState){
                if (!authState.isLoading) onNavigate(Graph.HOME)
            }
        }

        is AuthState.NotAuthenticated -> {
            if (authState.isLoading) LoadingScreen()
            LaunchedEffect(key1 = authState){
                if (!authState.isLoading) onNavigate(Graph.AUTH)
            }
        }
        is AuthState.Error -> {
            if (authState.isLoading) LoadingScreen() else ErrorScreen(message = (authState as AuthState.Error).error, onRetry = {authViewModel.retry()})

        }
    }
}