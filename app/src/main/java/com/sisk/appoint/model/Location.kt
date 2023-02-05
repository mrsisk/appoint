package com.sisk.appoint.model

import java.util.UUID

data class Location(val name: String = "", val latitude: Double = 0.0, val longitude: Double = 0.0, val id: String = UUID.randomUUID().toString())
