package com.sisk.appoint.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sisk.appoint.ui.account.AccountScreen
import com.sisk.appoint.ui.home.HomeScreen
import com.sisk.appoint.ui.location.LocationScreen
import com.sisk.appoint.ui.datetime.DateTimeScreen


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
        composable("location"){
            LocationScreen(
                navigateToDateTime = {
                    navController.navigate("dateTime")
                },
                onNavBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("dateTime"){
            DateTimeScreen(
                onNavBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
