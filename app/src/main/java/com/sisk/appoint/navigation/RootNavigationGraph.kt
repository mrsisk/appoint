package com.sisk.appoint.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sisk.appoint.ui.auth.ProfileRegistrationScreen
import com.sisk.appoint.ui.booking.BookingScaffold
import com.sisk.appoint.ui.home.HomeScaffold
import com.sisk.appoint.ui.swith.Switch

@Composable
fun RootNavigationGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Graph.SWITCH,
        route = Graph.ROOT
    ){
        composable(route = Graph.SWITCH){
            Switch(onNavigate = {
                navHostController.navigate(it){
                    popUpTo(Graph.SWITCH){inclusive = true}
                }
            })
        }
        authNavGraph(navHostController)


        composable(route = Graph.HOME){
            HomeScaffold(onNavigate = {
                navHostController.navigate(Graph.AUTH){
                    popUpTo(Graph.HOME){inclusive = true}
                }
            },
            navigateToBooking = {
                navHostController.navigate(Graph.BOOKING)
            })
        }

        composable(route = Graph.BOOKING){
            BookingScaffold()
        }
        composable(route = AppointDestinations.ProfileRegistration.route){
            ProfileRegistrationScreen{
                navHostController.navigate(Graph.SWITCH){
                    popUpTo(AppointDestinations.ProfileRegistration.route){inclusive = true}
                }
            }
        }
    }
}