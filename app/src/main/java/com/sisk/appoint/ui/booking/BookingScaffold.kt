package com.sisk.appoint.ui.booking

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sisk.appoint.model.Category
import com.sisk.appoint.navigation.BookingNavGraph

@Composable
fun BookingScaffold(
        navHostController: NavHostController = rememberNavController(),
        category: Category = Category.DENTIST,
        onComplete: () -> Unit = {}
) {

        BookingNavGraph(navHostController = navHostController, onComplete = onComplete, category = category)
}