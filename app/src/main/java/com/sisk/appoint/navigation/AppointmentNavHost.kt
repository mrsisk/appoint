package com.sisk.appoint.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sisk.appoint.ui.account.AccountScreen
import com.sisk.appoint.ui.home.HomeScreen
import com.sisk.appoint.ui.location.LocationScreen
import com.sisk.appoint.ui.datetime.DateTimeScreen
import com.sisk.appoint.viewmodel.BookingViewModel


@Composable
fun AppointmentNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier){
        composable("home"){
           HomeScreen(
               onCategorySelected = {
                   navController.navigate("booking")
               }
           )

        }
        bookingGraph(navController)
        composable("account"){
            AccountScreen()
        }
    }
}

fun NavGraphBuilder.bookingGraph(navController: NavHostController){
    navigation(startDestination = "location", route = "booking"){
        composable("location"){navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry("location")
            }
            val viewModel = hiltViewModel<BookingViewModel>(parentEntry)
            LocationScreen(
                navigateToDateTime = {
                    navController.navigate("dateTime")
                },
                onNavBack = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }

        composable("dateTime"){navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry("location")
            }
            val viewModel = hiltViewModel<BookingViewModel>(parentEntry)
            DateTimeScreen(
                onNavBack = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }
    }
}
