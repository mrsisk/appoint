package com.sisk.appoint.model

import com.sisk.appoint.R
import java.util.UUID

data class Category(val id: String = UUID.randomUUID().toString() ,val title: String, val image: Int)

