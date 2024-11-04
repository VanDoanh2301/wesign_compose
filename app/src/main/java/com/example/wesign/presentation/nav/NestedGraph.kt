package com.example.wesign.presentation.nav

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.wesign.presentation.ui.auth.login.LoginScreen
import com.example.wesign.presentation.ui.auth.otp.OtpScreen
import com.example.wesign.presentation.ui.auth.otp.OtpViewModel
import com.example.wesign.presentation.ui.auth.register.RegisterScreen
import com.example.wesign.presentation.ui.auth.register.RegisterViewModel
import com.example.wesign.presentation.ui.auth.success.SuccessScreen
import com.example.wesign.presentation.ui.main.home.HomeScreen
import com.example.wesign.presentation.ui.main.play.VideoPlayerScreen
import com.example.wesign.presentation.ui.main.topic.TopicScreen
import com.example.wesign.presentation.ui.main.vocabulary.VocabularyScreen


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
            val viewModel: OtpViewModel = hiltViewModel()
            val viewModelRegister: RegisterViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            OtpScreen(
                event = viewModel::onEvent,
                eventRegister = viewModelRegister::onEvent,
                state = state,
                onSuccessClick = {
                    appState.navigateWithPopUpTo(
                        AuthRoutes.Success.route,
                        inclusive = true,
                        popUpToRoute = AuthRoutes.OTP.route
                    )
                })
        }

        composable(AuthRoutes.Register.route) {
            val viewModel: RegisterViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            RegisterScreen(
                event = viewModel::onEvent,
                state = state,
                onOtpClick = {
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
        composable(MainRoutes.Vocabulary.route) {
            VocabularyScreen {
                appState.navigateWithPopUpTo(
                    MainRoutes.Play.route
                )
            }
        }
        composable(MainRoutes.Topic.route) {
            TopicScreen {
                appState.navigateWithPopUpTo(
                    MainRoutes.Vocabulary.route
                )
            }
        }
        composable(MainRoutes.Play.route) {
            VideoPlayerScreen()
        }

    }
}
