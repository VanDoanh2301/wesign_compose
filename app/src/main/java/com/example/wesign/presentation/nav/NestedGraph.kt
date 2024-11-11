package com.example.wesign.presentation.nav

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.presentation.ui.auth.login.LoginScreen
import com.example.wesign.presentation.ui.auth.login.LoginViewModel
import com.example.wesign.presentation.ui.auth.otp.OtpScreen
import com.example.wesign.presentation.ui.auth.otp.OtpViewModel
import com.example.wesign.presentation.ui.auth.register.RegisterScreen
import com.example.wesign.presentation.ui.auth.register.RegisterViewModel
import com.example.wesign.presentation.ui.auth.success.SuccessScreen
import com.example.wesign.presentation.ui.main.classroom.ClassRoomScreen
import com.example.wesign.presentation.ui.main.exam.ExamScreen
import com.example.wesign.presentation.ui.main.exam.ExamViewModel
import com.example.wesign.presentation.ui.main.home.HomeScreen
import com.example.wesign.presentation.ui.main.home.HomeViewModel
import com.example.wesign.presentation.ui.main.result.ResultTestScreen
import com.example.wesign.presentation.ui.main.search.SearchScreen
import com.example.wesign.presentation.ui.main.search.SearchViewModel
import com.example.wesign.presentation.ui.main.test.TestScreen
import com.example.wesign.presentation.ui.main.test.TestViewModel
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
            HomeScreen(
                appState,
                viewModel
            )
        }
        composable(MainRoutes.Vocabulary.route, arguments = listOf(
            navArgument(ARG_KEY_TOPIC_ID) {
                type = NavType.IntType
            },
            navArgument(ARG_KEY_TOPIC_NAME) {
                type = NavType.StringType
            }
        )) {
            val topicId = it.arguments?.getInt(ARG_KEY_TOPIC_ID)
            val name = it.arguments?.getString(ARG_KEY_TOPIC_NAME)
            val homeViewModel: HomeViewModel = hiltViewModel()
            val vocabularyState = homeViewModel.vocabularyState.collectAsLazyPagingItems()
            VocabularyScreen(
                name ?: "",
                topicId ?: -1,
                vocabularyState,
                homeViewModel::onEvent,
                appState
            ) { vocabulary ->
//                appState.navigateWithPopUpTo(MainRoutes.Play.route, params = mapOf(ARG_KEY_VOCABULARY to vocabulary))
            }
        }
        composable(MainRoutes.Topic.route, arguments = listOf(
            navArgument(ARG_KEY_CLASS_ROOM_ID) {
                type = NavType.IntType
            },
            navArgument(ARG_KEY_CLASS_ROOM_NAME) {
                type = NavType.StringType
            }
        )) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val topicState = homeViewModel.topicState.collectAsLazyPagingItems()
            val classRoomId = it.arguments?.getInt(ARG_KEY_CLASS_ROOM_ID)
            val className = it.arguments?.getString(ARG_KEY_CLASS_ROOM_NAME)

            TopicScreen(
                className ?: "",
                classRoomId ?: -1,
                topicState,
                 homeViewModel::onEvent,
                appState
            ) {topicId, name ->
                appState.navigateWithPopUpTo(
                    MainRoutes.Vocabulary.sendTopicIdAndName(topicId, name)
                )
            }
        }
        composable(MainRoutes.Play.route) {
            val vocabulary = appState.controller.previousBackStackEntry?.savedStateHandle?.get<Vocabulary>(ARG_KEY_VOCABULARY)
            if (vocabulary != null) {
//                VideoPlayerScreen(vocabulary)
            }
        }
        composable(MainRoutes.Search.route, arguments = listOf(
            navArgument(ARG_KEY_TYPE_SEARCH) {
                type = NavType.StringType
            },
            navArgument(ARG_KEY_TYPE_SEARCH_NAME) {
                type = NavType.StringType
            }
        )) {
            val typeSearch = it.arguments?.getString(ARG_KEY_TYPE_SEARCH)
            val name = it.arguments?.getString(ARG_KEY_TYPE_SEARCH_NAME)
            val searchViewModel :SearchViewModel = hiltViewModel()
            val vocabularyState = searchViewModel.vocabularyState.collectAsLazyPagingItems()
            val classRoomState = searchViewModel.classRoomState.collectAsLazyPagingItems()
            val topicState = searchViewModel.topicState.collectAsLazyPagingItems()

            SearchScreen(typeSearch, name, vocabularyState, classRoomState, topicState, searchViewModel::onEvent, appState)
        }
        composable(MainRoutes.ClassRoom.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val classRoomState = homeViewModel.classRoomState.collectAsLazyPagingItems()
            ClassRoomScreen(
                classRoomState,
                homeViewModel::onEvent,
                appState
            ) { classRoomId, name ->
                appState.navigateWithPopUpTo(
                    MainRoutes.Topic.sendClassRoomIdAndName(classRoomId, name)
                )
            }
        }

        composable(MainRoutes.Exam.route) {
            val viewModel: ExamViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            ExamScreen(state, viewModel::onEvent, onClickExam =  {
                appState.navigateWithPopUpTo(
                    MainRoutes.Question.sendClassRoomId(it)
                )
            }, onBackClick = {
                appState.popBackStack()
            })
        }

        composable(MainRoutes.Question.route, arguments = listOf(
            navArgument(ARG_KEY_CLASS_ROOM_ID_LIST) {
                type = NavType.IntType
            }
        )) {
            val viewModel: TestViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            val classRoomId= it.arguments?.getInt(ARG_KEY_CLASS_ROOM_ID_LIST)
            if (classRoomId!= null) {
                TestScreen(state, viewModel::onEvent, classRoomId , onFinishTest = { countCorrect, totalQuestion ->
                    appState.navigateWithPopUpTo(
                        MainRoutes.Result.sendCountCorrectAndTotalQuestion(countCorrect, totalQuestion),
                        popUpToRoute = MainRoutes.Question.route,
                        inclusive = true
                    )
                }, onBackClick = {
                    appState.popBackStack()
                }
                )
            }
        }
        composable(MainRoutes.Result.route, arguments = listOf(
            navArgument(ARG_KEY_COUNT_CORRECT) {
                type = NavType.IntType
            },
            navArgument(ARG_KEY_TOTAL_QUESTION) {
                type = NavType.IntType
            }
        )) {
            val countCorrect = it.arguments?.getInt(ARG_KEY_COUNT_CORRECT)
            val totalQuestion = it.arguments?.getInt(ARG_KEY_TOTAL_QUESTION)
            if (countCorrect != null && totalQuestion != null) {
                ResultTestScreen(
                    countCorrect,
                    totalQuestion

                ) {
                    appState.popBackStack()
                }
            }

        }

    }
}
