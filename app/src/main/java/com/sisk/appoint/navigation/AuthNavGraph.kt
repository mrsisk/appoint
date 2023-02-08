package com.sisk.appoint.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sisk.appoint.navigation.AppointDestinations.*
import com.sisk.appoint.ui.auth.*


fun NavGraphBuilder.authNavGraph(navHostController: NavHostController){
    navigation(route = Graph.AUTH, startDestination = Authentication.route){

        composable(route = Authentication.route){navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navHostController.getBackStackEntry(Authentication.route)
            }
            val authViewModel = hiltViewModel<AuthenticationViewModel>(parentEntry)
            AuthScreen(
                viewModel = authViewModel,
                onSelectOption = {
                    when(it){
                        AuthenticationMode.SIGN_UP -> {
                            authViewModel.setAuthMode(it)
                            navHostController.navigate(Register.route)
                        }
                        AuthenticationMode.SIGN_IN -> {
                            authViewModel.setAuthMode(it)
                            navHostController.navigate(SignIn.route)
                        }
                    }
                }
            )
        }

        composable(route = SignIn.route){navBackStackEntry ->

            val parentEntry = remember(navBackStackEntry) {
                navHostController.getBackStackEntry(Authentication.route)
            }
            val authViewModel = hiltViewModel<AuthenticationViewModel>(parentEntry)
            SignInScreen(viewModel = authViewModel){
                navHostController.navigate(Graph.HOME){
                    popUpTo(Graph.AUTH){inclusive = true}
                }
            }
        }

        composable(route = Register.route){navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navHostController.getBackStackEntry(Authentication.route)
            }
            val authViewModel = hiltViewModel<AuthenticationViewModel>(parentEntry)
            SignUpScreen(
                viewModel = authViewModel
            ){
                navHostController.navigate(Graph.HOME){
                    popUpTo(Graph.AUTH){inclusive = true}
                }
            }
        }
    }
}