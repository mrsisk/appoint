package com.sisk.appoint.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sisk.appoint.model.Category
import com.sisk.appoint.ui.datetime.DateTimeScreen
import com.sisk.appoint.ui.location.LocationScreen
import com.sisk.appoint.ui.review.ReviewScreen
import com.sisk.appoint.ui.viewmodel.BookingViewModel

@Composable
fun BookingNavGraph(
    navHostController: NavHostController,
    category: Category = Category.GENERIC,
    onComplete: () -> Unit = {}
) {

    NavHost(
        navController = navHostController,
        startDestination = AppointDestinations.Location.route,
        route = Graph.BOOKING
    ){
        composable(route = AppointDestinations.Location.route){ navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navHostController.getBackStackEntry(AppointDestinations.Location.route)
            }

            val viewModel = hiltViewModel<BookingViewModel>(parentEntry)
            viewModel.updateCategory(category)
            LocationScreen(
                viewModel = viewModel,
                onNavBack = {
                    navHostController.popBackStack()
                },
                navigateToDateTime = {
                    navHostController.navigate(AppointDestinations.DateTime.route)
                }
            )
        }

        composable(route = AppointDestinations.DateTime.route){navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navHostController.getBackStackEntry(AppointDestinations.Location.route)
            }

            val viewModel = hiltViewModel<BookingViewModel>(parentEntry)
            DateTimeScreen(
                viewModel = viewModel,
                onNavigateToReview = {
                    navHostController.navigate(AppointDestinations.Review.route)
                },
                onNavBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AppointDestinations.Review.route) { navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navHostController.getBackStackEntry(AppointDestinations.Location.route)
            }

            val viewModel = hiltViewModel<BookingViewModel>(parentEntry)
            ReviewScreen(viewModel = viewModel, onComplete = onComplete)
        }
    }
}