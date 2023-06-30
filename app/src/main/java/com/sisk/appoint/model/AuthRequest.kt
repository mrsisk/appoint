package com.sisk.appoint.model

data class AuthRequest(val email: String, val password: String)

data class RegistrationRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
