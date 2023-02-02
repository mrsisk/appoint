package com.sisk.appoint.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: NavBarItem("home", "Home", Icons.Default.Home)
    object Appointments: NavBarItem("appointment", "Appointment", Icons.Default.List)
    object Account: NavBarItem("account", "Account", Icons.Default.AccountCircle)
}
