package com.example.wesign.presentation.ui.main.test

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import com.example.wesign.presentation.component.DialogBottom
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.ui.main.test.components.CustomBoxTest
import com.example.wesign.presentation.ui.main.test.components.CustomDrawerItemTest
import com.example.wesign.presentation.ui.main.test.components.CustomTestTopBar
import com.example.wesign.presentation.ui.main.test.components.QuestionHeaderCard
import kotlinx.coroutines.launch

@Composable

fun TestScreen(
    state: TestScreenState,
    event: (TestScreenEvent) -> Unit,
    classRoomId: Int,
    onFinishTest: (Int, Int) -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        event.invoke(TestScreenEvent.GetQuestionByClassRoomId(classRoomId))
    }
    var isFinish by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )  {state.listTestQuestions.size}
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var isShowDialog by remember { mutableStateOf(false) }
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == state.listTestQuestions.size - 1) {
            isFinish = true
        } else {
            isFinish = false
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(vertical = WeSignDimension.PaddingExtraLarge),
                    drawerContainerColor = Color.White
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(WeSignDimension.PaddingMedium),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn {
                            items(state.listTestQuestions.size) { index ->
                                val currentQuestion =
                                    state.listTestQuestions[index]

                                CustomDrawerItemTest(
                                    question = "${index + 1}",
                                    answerA = currentQuestion.answers[0].content ?: "",
                                    answerB = currentQuestion.answers[1].content ?: "",
                                    answerC = currentQuestion.answers[2].content ?: "",
                                    answerD = currentQuestion.answers[3].content ?: "",
                                    answerCorrect = currentQuestion.answerCorrect,
                                    answerUser = currentQuestion.answerUser,
                                    isCurrentQuestion = pagerState.currentPage == index, onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }

            }, drawerState = drawerState, gesturesEnabled = true
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Scaffold(
                    topBar = {
                        CustomTestTopBar(
                            modifier = Modifier,
                            openDrawerListQuest = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            onBackClick = {
                                onBackClick()
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            onFinishTest = {
                                isShowDialog = true
//                                onFinishTest(
//                                    state.countCorrect,
//                                    state.totalQuestion
//                                )
                            },
                            titleRightTopBar = if (isFinish) "Kết quả" else "",
                        )
                    },
                    content = {
                        Column(modifier = Modifier.padding(it)) {
                            QuestionHeaderCard(
                                currentQuestion = pagerState.currentPage + 1,
                                totalQuestions = state.listTestQuestions.size,
                                onPrevious = {
                                    scope.launch {
                                        if (pagerState.currentPage > 0) {
                                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                        }
                                    }

                                },
                                onNext = {
                                    scope.launch {
                                        if (pagerState.currentPage < state.listTestQuestions.size - 1) {
                                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                        }
                                    }
                                },
                            )
                            CustomBoxTest(
                                modifier = Modifier,
                                pagerState,
                                state.listTestQuestions,
                                event
                            )
                        }
                    }
                )
            }
        }
    }
    AnimatedVisibility(isShowDialog) {
        DialogBottom(
            title = "Bạn có muốn kết thúc bài kiểm tra?",
            onDismissRequest = {
                isShowDialog = false
            },
            onConfirmation = {
                isShowDialog = false
                 onFinishTest(
                     state.countCorrect,
                     state.totalQuestion
                 )
            }
        )
    }
}