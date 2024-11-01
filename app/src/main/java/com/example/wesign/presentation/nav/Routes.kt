package com.example.wesign.presentation.nav

const val ARG_KEY_EMAIL = "arg_key_email"

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val MAIN_GRAPH_ROUTE = "main"
const val SPLASH_GRAPH_ROUTE = "splash"
const val ONBOARD_GRAPH_ROUTE = "onboard"

// Auth Routes
const val AUTH_LOGIN_ROUTE = "auth/login"
const val AUTH_REGISTER_ROUTE = "auth/register"
const val AUTH_OTP_ROUTE = "auth/otp/{$ARG_KEY_EMAIL}"
const val AUTH_SUCCESS_ROUTE = "auth/success"

// Main Routes
const val MAIN_HOME_ROUTE = "main/home"
const val MAIN_TEST_ROUTE = "main/test"
const val MAIN_UPDATE_ROUTE = "main/update"
const val MAIN_TOPIC_ROUTE = "main/home/topic"
const val MAIN_VOCABULARY_ROUTE = "main/home/topic/vocabulary"
const val MAIN_PLAY_ROUTE = "main/home/topic/vocabulary/play"

// Bottom Home Routes
const val BOTTOM_HOME_ROUTE = "home_page"
const val BOTTOM_LEARN_ROUTE = "learn_page"
const val BOTTOM_PROFILE_ROUTE = "profile_page"

sealed class Screen(val route: String) {
    object Splash : Screen(SPLASH_GRAPH_ROUTE)
    object OnBoard : Screen(ONBOARD_GRAPH_ROUTE)
    object Auth : Screen(AUTH_GRAPH_ROUTE)
    object Main : Screen(MAIN_GRAPH_ROUTE)
}

sealed class AuthRoutes(route: String) : Screen(route) {
    object Login : AuthRoutes(AUTH_LOGIN_ROUTE)
    object Register : AuthRoutes(AUTH_REGISTER_ROUTE)

    // OTP screen for authentication
    object OTP : AuthRoutes(AUTH_OTP_ROUTE) {
        /**
         * Constructs the route for the OTP screen by replacing the email placeholder.
         * @param email: Email of the user
         * @return The formatted route string with the email.
         */
        fun sendEmail(email: String): String {
            return AUTH_OTP_ROUTE.replace("{$ARG_KEY_EMAIL}", email)
        }
    }

    object Success : AuthRoutes(AUTH_SUCCESS_ROUTE)
}

sealed class MainRoutes(route: String) : Screen(route) {
    object Home : MainRoutes(MAIN_HOME_ROUTE)
    object Test : MainRoutes(MAIN_TEST_ROUTE)
    object Update : MainRoutes(MAIN_UPDATE_ROUTE)
    object Topic : MainRoutes(MAIN_TOPIC_ROUTE)
    object Vocabulary : MainRoutes(MAIN_VOCABULARY_ROUTE)
    object Play : MainRoutes(MAIN_PLAY_ROUTE)
}

sealed class BottomHomeRoutes(route: String) : Screen(route) {
    object BottomHome : BottomHomeRoutes(BOTTOM_HOME_ROUTE)
    object Learn : BottomHomeRoutes(BOTTOM_LEARN_ROUTE)
    object Profile : BottomHomeRoutes(BOTTOM_PROFILE_ROUTE)
}
