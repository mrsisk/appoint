package com.sisk.appoint.model

data class AppointUser(
    val email: String,
    val createdAt: String,
    val roles: Set<Role>,
    val profile: Profile? = null
)


data class Role(val id: Long, val name: RoleTye)

enum class RoleTye{
    USER,
    ADMIN
}