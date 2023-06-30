package com.sisk.appoint.model

data class AppointUser(
    val sub: String = "",
    val emailVerified: Boolean = false,
    val name: String? = null,
    val preferred_username: String? = null ,
    val given_name: String? = null,
    val family_name: String? = null,
    val email:String = "guest@mail.com"
)


data class Role(val id: Long, val name: RoleTye)

enum class RoleTye{
    USER,
    ADMIN
}