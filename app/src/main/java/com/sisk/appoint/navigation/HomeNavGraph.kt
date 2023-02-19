package com.sisk.appoint.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sisk.appoint.ui.account.AccountScreen
import com.sisk.appoint.ui.home.HomeScreen

@Composable
fun HomeNavGraph(navHostController: NavHostController, modifier: Modifier){
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = AppointDestinations.Home.route,
        modifier = modifier
    ){
        composable(AppointDestinations.Home.route){
            HomeScreen()

        }

        composable(AppointDestinations.Account.route){
            AccountScreen()
        }


    }
}