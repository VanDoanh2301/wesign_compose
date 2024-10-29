package com.example.wesign.presentation.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation


fun NavGraphBuilder.authGraph(appState: WeSignAppState) {
    navigation(startDestination = AuthRoutes.Login.route, route = Screen.Auth.route) {
        composable( AuthRoutes.Login.route) {

        }

        composable( AuthRoutes.OTP.route) {

        }

        composable( AuthRoutes.Register.route) {

        }

        composable( AuthRoutes.Success.route) {
        }
    }
}
