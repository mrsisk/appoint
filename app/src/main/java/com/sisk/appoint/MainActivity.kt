package com.sisk.appoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sisk.appoint.model.User
import com.sisk.appoint.navigation.AppointmentNavHost
import com.sisk.appoint.navigation.bottomBarScreens
import com.sisk.appoint.ui.components.BottomNavigation
import com.sisk.appoint.ui.components.TopBar
import com.sisk.appoint.ui.theme.AppointTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppointTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                val currentDestination = navBackStackEntry?.destination?.route ?: ""
                DisposableEffect(systemUiController, useDarkIcons) {
                    // Update all of the system bar colors to be transparent, and use
                    // dark icons if we're in light theme
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )

                    // setStatusBarColor() and setNavigationBarColor() also exist

                    onDispose {}
                }
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Scaffold(
                        modifier = Modifier,
                        bottomBar = {
                            if (currentDestination in bottomBarScreens.map { it.route }) {
                                BottomNavigation(navController)
                            }
                        },
                        topBar = {
                            if(currentDestination == "home") {
                                TopBar(
                                    user = User(
                                        username = "John sisk",
                                        email = "sisk@gmail.com",
                                        image = ""
                                    ), scrollBehavior = scrollBehavior
                                )
                            }
                        }
                    ) {innerPadding ->
                        AppointmentNavHost(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                }
            }
        }
    }

}

