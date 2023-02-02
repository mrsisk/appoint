package com.sisk.appoint.model

import com.sisk.appoint.R

data class Category(val title: String, val image: Int)

val categories = listOf<Category>(
    Category("Heart specialist", image = R.drawable.heart),
    Category("Dentist", image = R.drawable.tooth),
    Category("Eyes specialist", R.drawable.eye),
    Category("Maternity", R.drawable.heart)
)