package com.example.wesign.presentation.ui.main.home.learn_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.LazyPagingItems
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Topic
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.presentation.nav.ARG_KEY_VOCABULARY
import com.example.wesign.presentation.nav.MainRoutes
import com.example.wesign.presentation.nav.WeSignAppState
import com.example.wesign.presentation.ui.main.home.HomeScreenEvent
import com.example.wesign.presentation.ui.main.home.learn_page.components.Tabs
import com.example.wesign.presentation.ui.main.home.learn_page.components.TabsContent
import com.example.wesign.presentation.ui.main.home.learn_page.components.list

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LearnPageScreen(
    appState: WeSignAppState,
    classRoomState:  LazyPagingItems<ClassRoom>,
    vocabularyState: LazyPagingItems<Vocabulary>,
    topicState: LazyPagingItems<Topic>,
    event:  (HomeScreenEvent) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { list.size })
    LaunchedEffect (Unit) {
        event(HomeScreenEvent.GetAllClassRooms)
        event(HomeScreenEvent.GetAllVocabularies())
        event(HomeScreenEvent.GetAllTopics())
    }
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(
            classRoomState = classRoomState,
            vocabularyState = vocabularyState,
            topicState = topicState,
            pagerState = pagerState,
            onClickClass = {classRoomId, name ->
                appState.navigateWithPopUpTo(
                    MainRoutes.Lesson.sendClassRoomId(
                        classRoomId,
                    )
                )
            },
            onClickTopic = {topicId, name ->
                appState.navigateWithPopUpTo(
                    MainRoutes.Vocabulary.sendTopicIdAndName(
                        topicId,
                        name
                    )
                )
            },
            onClickVocal = { vocabulary ->
                appState.navigateWithPopUpTo(MainRoutes.Play.route, params = mapOf(ARG_KEY_VOCABULARY to vocabulary))
            },
            onClickAlphabet = {

            },
            onClickNumber = {

            }

        )
    }
}






