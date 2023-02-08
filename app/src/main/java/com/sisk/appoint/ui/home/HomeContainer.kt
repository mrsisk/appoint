package com.sisk.appoint.ui.home

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sisk.appoint.model.User
import com.sisk.appoint.navigation.HomeNavGraph
import com.sisk.appoint.navigation.bottomBarScreens
import com.sisk.appoint.ui.components.BottomNav
import com.sisk.appoint.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContainer(
    navHostController: NavHostController = rememberNavController()
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: "home"

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            if (currentDestination in bottomBarScreens.map { it.route }) {

                BottomNav(currentDestination = currentDestination, onClick = {
                    navHostController.navigate(it)
                })
            }else{
                Log.d("mama", "current is $currentDestination in ${bottomBarScreens.map { it.route }}")
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
        HomeNavGraph(navHostController = navHostController, modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
fun HomePrev() {
    //Home()
}