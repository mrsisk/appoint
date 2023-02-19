package com.sisk.appoint.ui.auth

data class ProfileRegistrationState(
    val name: String = "",
    val surname: String = "",
    val dob: String = "",
    val cell: String = "",
    val genderOptions: List<String> = listOf("Male", "Female"),
    val gender: String = genderOptions.first(),
    val loading: Boolean = false
)