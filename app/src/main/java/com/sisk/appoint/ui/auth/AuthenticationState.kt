package com.sisk.appoint.ui.auth

data class AuthenticationState(
    val email: String? = null,
    val password: String? = null,
    val authenticationMode: AuthenticationMode = AuthenticationMode.SIGN_IN,
    val loading: Boolean = false,
    val error: String? = null
)

enum class AuthenticationMode {
    SIGN_UP, SIGN_IN
}