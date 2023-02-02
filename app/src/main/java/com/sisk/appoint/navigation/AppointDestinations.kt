package com.sisk.appoint.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppointDestinations(val title: String, val route: String, val icon: ImageVector){
    object Home: AppointDestinations(title = "Home", route = "home", icon = Icons.Outlined.Home)
    object Account: AppointDestinations(title = "Account", route = "account", icon = Icons.Outlined.AccountBox)
    object Location: AppointDestinations(title = "Location", route = "location", icon = Icons.Outlined.LocationOn)
}

val bottomBarScreens = listOf(AppointDestinations.Home, AppointDestinations.Account)