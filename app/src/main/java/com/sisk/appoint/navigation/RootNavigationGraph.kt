package com.sisk.appoint.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sisk.appoint.ui.home.HomeContainer

@Composable
fun RootNavigationGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Graph.AUTH,
        route = Graph.ROOT
    ){
        authNavGraph(navHostController)

        composable(route = Graph.HOME){
            HomeContainer()
        }
    }
}