package com.sisk.appoint.model

import java.util.UUID

data class CategoryUi(val id: Category = Category.DENTIST, val title: String = "", val image: Int = 1)

enum class Category{
    HEART,
    DENTIST,
    EYES,
    MATERNITY,
    GENERIC
}

