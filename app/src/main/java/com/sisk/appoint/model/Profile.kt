package com.sisk.appoint.model

data class Profile(
    val id: Long,
    val name: String,
    val surname: String,
    val phone: String,
    val dob: String,
    val createdAt: String,
    val gender: Gender
)

enum class Gender{
    MALE,
    FEMALE
}