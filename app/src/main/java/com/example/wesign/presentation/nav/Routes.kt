package com.example.wesign.presentation.nav

const val ARG_KEY_EMAIL = "arg_key_email"

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val MAIN_GRAPH_ROUTE = "main"
const val SPLASH_GRAPH_ROUTE = "splash"
const val ONBOARD_GRAPH_ROUTE = "onboard"


sealed class Screen(val route: String) {
    data object Splash : Screen(SPLASH_GRAPH_ROUTE)
    // Onboarding Screens
    data object OnBoard: Screen(ONBOARD_GRAPH_ROUTE)
    // Auth Screens
    data object Auth : Screen(AUTH_GRAPH_ROUTE)
    // Main Screens
    data object Main: Screen(MAIN_GRAPH_ROUTE)
}

sealed class AuthRoutes(route: String) : Screen(route) {
    object Login : AuthRoutes("auth/login")
    object Register : AuthRoutes("auth/register")

    // OTP screen for authentication
    object OTP : AuthRoutes("auth/otp/{$ARG_KEY_EMAIL}") {
        /**
         * Constructs the route for OTP screen by replacing the email placeholder.
         * @param email : Email of the user
         * @return The formatted route string with the email.
         */
        fun sendEmail(email: String): String {
            return "auth/otp/$email".replace("{$ARG_KEY_EMAIL}", email)
        }
    }

    object Success : AuthRoutes("auth/success")
}

sealed class MainRoutes(route: String) : Screen(route) {
    object Home : MainRoutes("main/home")
    object Test : MainRoutes("main/test")
    object Update : MainRoutes("main/update")
}

sealed class BottomHomeRoutes(route: String) : Screen(route) {
    object BottomHome : BottomHomeRoutes("home_page")
    object Learn : BottomHomeRoutes("learn_page")
    object Profile : BottomHomeRoutes("profile_page")
}
