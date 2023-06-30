package com.sisk.appoint.model

data class GenericError(val message: String? = null)

data class AuthError(val error: String?, val error_description: String?)
