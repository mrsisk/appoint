package com.sisk.appoint.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sisk.appoint.ui.AppointViewModel
import com.sisk.appoint.ui.account.AccountScreen
import com.sisk.appoint.ui.home.HomeScreen
import com.sisk.appoint.ui.location.LocationScreen
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AppointmentNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: AppointViewModel = viewModel()
) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier){
        composable("home"){
           HomeScreen(navController)

        }
        bookingGraph(navController, viewModel)
        composable("account"){
            AccountScreen()
        }
    }
}

fun NavGraphBuilder.bookingGraph(navController: NavHostController, viewModel: AppointViewModel){
    navigation(startDestination = "location", route = "booking"){
        composable("location"){
            LocationScreen(viewModel)
        }
    }
}
