package com.example.wesign.presentation.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.wesign.presentation.ui.auth.login.LoginScreen
import com.example.wesign.presentation.ui.auth.otp.OtpScreen
import com.example.wesign.presentation.ui.auth.register.RegisterScreen
import com.example.wesign.presentation.ui.auth.success.SuccessScreen
import com.example.wesign.presentation.ui.main.home.HomeScreen


fun NavGraphBuilder.authGraph(appState: WeSignAppState) {
    navigation(startDestination = AuthRoutes.Login.route, route = Screen.Auth.route) {
        composable(AuthRoutes.Login.route) {
            LoginScreen(onRegisterClick = {
                appState.navigateWithPopUpTo(AuthRoutes.Register.route)
            }, onLoginClick = {
                appState.navigateWithPopUpTo(Screen.Main.route)
            })
        }

        composable(AuthRoutes.OTP.route) {
            OtpScreen(onSuccessClick = {
                appState.navigateWithPopUpTo(AuthRoutes.Success.route)
            })
        }

        composable(AuthRoutes.Register.route) {
            RegisterScreen(onOtpClick = {
                appState.navigateWithPopUpTo(AuthRoutes.OTP.route)
            })
        }

        composable(AuthRoutes.Success.route) {
            SuccessScreen()
        }
    }
}

fun NavGraphBuilder.mainGraph(appState: WeSignAppState) {
    navigation(startDestination = MainRoutes.Home.route, route = Screen.Main.route) {
        composable(MainRoutes.Home.route) {
            HomeScreen(appState)
        }
        composable(MainRoutes.Test.route) {

        }
        composable(MainRoutes.Topic.route) {

        }
    }
}
