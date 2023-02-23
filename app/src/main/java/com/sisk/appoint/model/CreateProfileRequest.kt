package com.sisk.appoint.model

data class CreateProfileRequest(
    val name: String,
    val surname: String,
    val phone: String,
    val dob: String,
    val gender: String
)
