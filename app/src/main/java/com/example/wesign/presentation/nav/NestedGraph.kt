package com.example.wesign.presentation.nav

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.wesign.presentation.ui.auth.login.LoginScreen
import com.example.wesign.presentation.ui.auth.login.LoginViewModel
import com.example.wesign.presentation.ui.auth.otp.OtpScreen
import com.example.wesign.presentation.ui.auth.otp.OtpViewModel
import com.example.wesign.presentation.ui.auth.register.RegisterScreen
import com.example.wesign.presentation.ui.auth.register.RegisterViewModel
import com.example.wesign.presentation.ui.auth.success.SuccessScreen
import com.example.wesign.presentation.ui.main.home.HomeScreen
import com.example.wesign.presentation.ui.main.home.HomeViewModel
import com.example.wesign.presentation.ui.main.play.VideoPlayerScreen
import com.example.wesign.presentation.ui.main.topic.TopicScreen
import com.example.wesign.presentation.ui.main.vocabulary.VocabularyScreen


fun NavGraphBuilder.authGraph(appState: WeSignAppState) {
    navigation(startDestination = AuthRoutes.Login.route, route = Screen.Auth.route) {
        composable(AuthRoutes.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            LoginScreen(
                state = state,
                event = viewModel::onEvent,
                onRegisterClick = {
                appState.navigateWithPopUpTo(AuthRoutes.Register.route)
            }, onLoginClick = {
                appState.navigateWithPopUpTo(Screen.Main.route)
            })
        }

        composable(AuthRoutes.OTP.route, arguments = listOf(
            navArgument(ARG_KEY_EMAIL) {
                type = NavType.StringType
            },
            navArgument(ARG_KEY_NAME) {
                type = NavType.StringType
            },
            navArgument(ARG_KEY_PASSWORD) {
                type = NavType.StringType
            }
        )) {
            val viewModel: OtpViewModel = hiltViewModel()
            val viewModelRegister: RegisterViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            val email = it.arguments?.getString(ARG_KEY_EMAIL)
            val name = it.arguments?.getString(ARG_KEY_NAME)
            val password = it.arguments?.getString(ARG_KEY_PASSWORD)

            OtpScreen(
                event = viewModel::onEvent,
                eventRegister = viewModelRegister::onEvent,
                state = state,
                email = email ?: "",
                name = name ?: "",
                password = password ?: "",
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
                onOtpClick = { email, name, password ->
                    appState.navigateWithPopUpTo(AuthRoutes.OTP.sendEmail(email, name, password))
                })
        }

        composable(AuthRoutes.Success.route) {
            SuccessScreen(onHomeClick = {
                appState.navigateWithPopUpTo(
                    AuthRoutes.Login.route,
                    inclusive = true,
                    popUpToRoute = AuthRoutes.Success.route
                )
            })
        }
    }
}

fun NavGraphBuilder.mainGraph(appState: WeSignAppState) {
    navigation(startDestination = MainRoutes.Home.route, route = Screen.Main.route) {
        composable(MainRoutes.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            HomeScreen(
                appState,
                homeState = state)
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
