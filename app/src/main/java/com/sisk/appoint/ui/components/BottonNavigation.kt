package com.sisk.appoint.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sisk.appoint.navigation.bottomBarScreens

@Composable
fun BottomNavigation(controller: NavHostController) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{

        bottomBarScreens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                alwaysShowLabel = false, // Hides the title for the unselected items
                onClick = {
                    controller.navigate(screen.route){
                        popUpTo(controller.graph.findStartDestination().id){
                            //saveState = true
                        }
                        launchSingleTop = true
                       // restoreState = true
                    }
                }
            )
        }
    }
}