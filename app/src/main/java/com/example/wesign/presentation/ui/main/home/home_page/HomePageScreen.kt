package com.example.wesign.presentation.ui.main.home.home_page

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import com.example.camera_ai.PracticeDetectorActivity
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.presentation.nav.MainRoutes
import com.example.wesign.presentation.nav.Screen
import com.example.wesign.presentation.nav.WeSignAppState
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.ui.main.home.HomeScreenEvent
import com.example.wesign.presentation.ui.main.home.UserDetailState
import com.example.wesign.presentation.ui.main.home.components.CustomTopAppBar
import com.example.wesign.presentation.ui.main.home.home_page.components.CoursesGrid
import com.example.wesign.presentation.ui.main.home.home_page.components.RecommendedClassroomsRow
import com.example.wesign.presentation.ui.main.home.home_page.components.RecommendedVocabularyRow
import com.example.wesign.presentation.ui.main.home.home_page.components.SearchContent
import com.example.wesign.presentation.ui.main.home.home_page.components.SlideContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePageScreen(
    appState: WeSignAppState,
    userState: UserDetailState,
    classRoomState: LazyPagingItems<ClassRoom>,
    vocabularyState: LazyPagingItems<Vocabulary>,
    event: (HomeScreenEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        event(HomeScreenEvent.GetAllClassRooms)
        event(HomeScreenEvent.GetAllVocabularies())
        event(HomeScreenEvent.GetUserDetail)
    }

    val activity = LocalContext.current as Activity
    var typeSearch by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = WeSignDimension.PaddingLarge,
                    end = WeSignDimension.PaddingLarge,
                    top = WeSignDimension.PaddingLarge
                )
        ) {
            item {
                CustomTopAppBar(userState.userDetail, onTextChanged = {
                    appState.navigateWithPopUpTo(
                        MainRoutes.Search.sendTypeSearch(
                            typeSearch,
                            it
                        )
                    )
                }, onNextIntro = {
                    appState.navigateWithPopUpTo(Screen.Intro.sendIntro(it))
                })
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingExtraLarge))
            }
            item {
                SearchContent(
                    onSearch = { typeSearch, textSearch ->
                        appState.navigateWithPopUpTo(
                            MainRoutes.Search.sendTypeSearch(
                                typeSearch,
                                textSearch
                            )
                        )
                    }, addTypeChange = {
                        typeSearch = it
                    })
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
            }
            item {
                SlideContent()
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
            }
            item {
                CoursesGrid(onClickNext = {
                    when (it) {
                        0 -> {
                            appState.navigateWithPopUpTo(
                                MainRoutes.Topic.sendClassRoomIdAndName(
                                    -1,
                                    "Tất cả"
                                )
                            )
                        }

                        1 -> {
                            appState.navigateWithPopUpTo(MainRoutes.ClassRoom.route)
                        }

                        2 -> appState.navigateWithPopUpTo(MainRoutes.Exam.route)
                        3 -> {
                            activity.startActivity(
                                Intent(
                                    activity,
                                    PracticeDetectorActivity::class.java
                                )
                            )
                        }

                        else -> {}
                    }
                })
            }
            item {
                RecommendedClassroomsRow(
                    title = "Gợi ý lớp học",
                    classrooms = classRoomState
                ) { classId, content ->
                    appState.navigateWithPopUpTo(
                        MainRoutes.Topic.sendClassRoomIdAndName(
                            classId,
                            content
                        )
                    )
                }
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
            }
            item {
                RecommendedVocabularyRow(title = "Gợi ý từ vựng", vocabularies = vocabularyState)
            }

        }
    }
}












