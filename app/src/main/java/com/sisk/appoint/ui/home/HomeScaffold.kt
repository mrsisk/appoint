package com.sisk.appoint.ui.home

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sisk.appoint.model.User
import com.sisk.appoint.navigation.HomeNavGraph
import com.sisk.appoint.navigation.bottomBarScreens
import com.sisk.appoint.ui.components.BottomNav
import com.sisk.appoint.ui.components.LoadingScreen
import com.sisk.appoint.ui.components.TopBar
import com.sisk.appoint.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffold(
    navHostController: NavHostController = rememberNavController(),
    viewHomeModel: HomeViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onNavigate: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route ?: "home"
    val state by viewHomeModel.uiState.collectAsState()


    DisposableEffect(key1 = lifecycleOwner){
        val observer = LifecycleEventObserver{_, event ->
            when(event){
                Lifecycle.Event.ON_CREATE -> Log.d("mama s", "onCreate")
                Lifecycle.Event.ON_START -> Log.d("mama s", "onStart")
                Lifecycle.Event.ON_RESUME ->{
                 viewHomeModel.checkAuth()
                }
                Lifecycle.Event.ON_PAUSE -> Log.d("mama s", "onPause")
                Lifecycle.Event.ON_STOP -> Log.d("mama s", "onStop")
                Lifecycle.Event.ON_DESTROY -> Log.d("mama s", "onDestroy")
                Lifecycle.Event.ON_ANY -> Log.d("mama s", "ANY")
            }

        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    if (state.isLoading) LoadingScreen()
    else if (state.user != null) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            bottomBar = {
                if (currentDestination in bottomBarScreens.map { it.route }) {

                    BottomNav(currentDestination = currentDestination, onClick = {
                        navHostController.navigate(it)
                    })
                } else {
                    Log.d(
                        "mama",
                        "current is $currentDestination in ${bottomBarScreens.map { it.route }}"
                    )
                }
            },
            topBar = {
                if (currentDestination == "home") {
                    TopBar(
                        user = User(
                            username = "John sisk",
                            email = "sisk@gmail.com",
                            image = ""
                        ), scrollBehavior = scrollBehavior
                    )
                }
            }
        ) { innerPadding ->
            HomeNavGraph(
                navHostController = navHostController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
    else{
        LaunchedEffect(key1 = state){
            onNavigate()
        }
    }
}