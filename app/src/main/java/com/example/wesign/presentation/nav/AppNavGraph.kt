package com.example.wesign.presentation.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

        }
        composable(Screen.OnBoard.route) {

        }
        // Auth Graph
        authGraph(appState)

        // Main Graph
//        mainGraph(appState)

    }
}


class WeSignAppState(
    private val coroutineScope: CoroutineScope,
    val controller: NavHostController
) {
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
