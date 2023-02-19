package com.sisk.appoint.ui.swith

import com.sisk.appoint.model.AppointUser

sealed interface AuthState{

    val isLoading: Boolean

    data class NoProfile(override val isLoading: Boolean): AuthState

    data class NotAuthenticated(override val isLoading: Boolean): AuthState

    data class Authenticated(val user: AppointUser, override val isLoading: Boolean): AuthState

    data class Error(override val isLoading: Boolean, val error: String): AuthState
}

data class AuthViewModelState(
    val user: AppointUser? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val error: String? = null
) {
    fun toAuthState(): AuthState {

        if (hasError) return AuthState.Error(isLoading = isLoading, error = error ?: "Unknown error")

        if (user == null) return AuthState.NotAuthenticated(isLoading = isLoading)

        if (user.profile == null) return AuthState.NoProfile(isLoading = isLoading)

        return AuthState.Authenticated(user, isLoading = isLoading)
    }
}