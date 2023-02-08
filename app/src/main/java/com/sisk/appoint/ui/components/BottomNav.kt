package com.sisk.appoint.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sisk.appoint.navigation.AppointDestinations
import com.sisk.appoint.navigation.bottomBarScreens

@Composable
fun BottomNav(currentDestination: String = AppointDestinations.Home.route, onClick: (String) -> Unit = {}) {

    NavigationBar{

        bottomBarScreens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentDestination == screen.route,
                alwaysShowLabel = false, // Hides the title for the unselected items
                onClick = {
                    onClick(screen.route)
                    }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavPreview() {
    BottomNav()
}
