package com.example.wesign.presentation.nav

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wesign.presentation.ui.main.home.HomeViewModel
import com.example.wesign.presentation.ui.main.home.home_page.HomePageScreen
import com.example.wesign.presentation.ui.main.home.learn_page.LearnPageScreen
import com.example.wesign.presentation.ui.main.home.profile_page.ProfilePageScreen
import com.example.wesign.presentation.ui.onboar.OnBoardingScreen
import com.example.wesign.presentation.ui.onboar.OnBoardingViewModel
import com.example.wesign.presentation.ui.splash.SplashScreen
import com.example.wesign.presentation.ui.splash.SplashViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(appState: WeSignAppState = rememberWeSignAppState()) {
    AnimatedNavHost(
        navController = appState.controller,
        startDestination = Screen.Splash.route,
        route = ROOT_GRAPH_ROUTE
    ) {
        composable(Screen.Splash.route) {
            val viewModel: SplashViewModel = hiltViewModel()
            val token = viewModel.token.collectAsState().value
            val isFirstApp = viewModel.isFirstApp.collectAsState().value
            SplashScreen(onSplashFinished = {
                if (isFirstApp) {
                    appState.navigateWithPopUpTo(Screen.OnBoard.route)
                } else {
                    if (token.isNotEmpty()) {
                        appState.navigateWithPopUpTo(Screen.Main.route)
                    } else {
                        appState.navigateWithPopUpTo(Screen.Auth.route)
                    }
                }
            })
            viewModel.isFirstApp.collectAsState().value.let {
                if (it) {
                    SplashScreen(onSplashFinished = {
                        appState.navigateWithPopUpTo(Screen.OnBoard.route)
                    })
                } else {
                    if (viewModel.token.value.isNotEmpty()) {
                        SplashScreen(onSplashFinished = {
                            appState.navigateWithPopUpTo(Screen.Main.route)
                        })
                    } else {
                        SplashScreen(onSplashFinished = {
                            appState.navigateWithPopUpTo(Screen.Auth.route)
                        })
                    }

                }
            }

        }
        composable(Screen.OnBoard.route) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(
                event = viewModel::onEvent,
                onBoardingFinished = {
                    appState.navigateWithPopUpTo(Screen.Auth.route)
                })
        }
        // Auth Graph
        authGraph(appState)

        // Main Graph
        mainGraph(appState)

    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    appState: WeSignAppState,
    viewModel: HomeViewModel
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = BottomHomeRoutes.BottomHome.route,
        route = MainRoutes.Home.route
    ) {
        composable(BottomHomeRoutes.BottomHome.route) {
            val userState by viewModel.userDetailState.collectAsState()
            val classRoomState =viewModel.classRoomState.collectAsLazyPagingItems()

            HomePageScreen(userState,  classRoomState, viewModel::onEvent)
        }
        composable(BottomHomeRoutes.Learn.route) {
            LearnPageScreen(appState)
        }
        composable(BottomHomeRoutes.Profile.route) {
            ProfilePageScreen(appState)
        }
    }
}

class WeSignAppState(
    private val coroutineScope: CoroutineScope,
    val controller: NavHostController
) {

    fun getCurrentRoute(): String? = controller.currentDestination?.route

    fun currentDestinationIs(route: String): Boolean =
        controller.currentBackStackEntry?.destination?.route == route

    fun <T> getDataFromNextScreen(key: String, defaultValue: T): StateFlow<T>? =
        controller.currentBackStackEntry?.savedStateHandle?.getStateFlow(key, defaultValue)

    fun <T> removeDataFromNextScreen(key: String) {
        controller.currentBackStackEntry?.savedStateHandle?.remove<T>(key)
    }

    fun popBackStack(popToRoute: String? = null, params: Map<String, Any>? = null) {
        if (popToRoute == null) {
            params?.forEach { data ->
                controller.previousBackStackEntry?.savedStateHandle?.set(data.key, data.value)
            }
            controller.popBackStack()
        } else {
            params?.forEach { data ->
                controller.getBackStackEntry(popToRoute).savedStateHandle[data.key] = data.value
            }
            controller.popBackStack(route = popToRoute, inclusive = false)
        }
    }

    fun navigateWithPopUpTo(
        destinationRoute: String,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        params: Map<String, Any>? = null
    ) {
        params?.forEach { data ->
            controller.getBackStackEntry(destinationRoute).savedStateHandle[data.key] = data.value
        }

        controller.navigate(destinationRoute) {
            popUpToRoute?.let {
                popUpTo(it) {
                    this.inclusive = inclusive
                }
            }
        }
    }


}

@Composable
fun rememberWeSignAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    controller: NavHostController = rememberNavController()
): WeSignAppState = remember {
    WeSignAppState(coroutineScope, controller)
}
