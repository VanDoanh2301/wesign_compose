package com.example.wesign.presentation.ui.main.home.learn_page.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.wesign.R
import com.example.wesign.presentation.theme.BlueStart
import com.example.wesign.presentation.theme.GreenStart
import com.example.wesign.presentation.theme.OrangeStart
import com.example.wesign.presentation.theme.PurpleStart
import kotlinx.coroutines.launch


@Composable
fun Tabs(pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    val backgroundColor by animateColorAsState(
        when (pagerState.currentPage) {
            0 -> PurpleStart
            1 -> BlueStart
            2 -> OrangeStart
            3 -> GreenStart
            4 -> GreenStart
            else -> GreenStart
        }
    )
    ScrollableTabRow(
        modifier = Modifier.height(62.dp),
        edgePadding = 0.dp,
        divider = {
            Spacer(modifier = Modifier.height(0.dp))
        },
        selectedTabIndex = pagerState.currentPage,
        containerColor = backgroundColor,
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .zIndex(-1f)
                    .fillMaxWidth()
            ) {
                TabLayoutIndicator(
                    tabPositions = tabPositions,
                    tabPage = when (pagerState.currentPage) {
                        0 -> TabPage.Class
                        1 -> TabPage.Topic
                        2 -> TabPage.Vocal
                        3 -> TabPage.Number
                        4 -> TabPage.Alpha
                        else -> TabPage.Alpha
                    },
                )
            }
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = list[index].icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = list[index].title,
                            color = if (pagerState.currentPage == index) Color.Black else Color.White.copy(
                                alpha = 0.5f
                            ),
                            fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }

                }
            )
        }
    }
}

@Composable
fun TabsContent(
    pagerState: PagerState,
    onClickClass: () -> Unit = { },
    onClickTopic: () -> Unit = { },
    onClickVocal: () -> Unit = { },
    onClickNumber: () -> Unit = { },
    onClickAlphabet: () -> Unit = { }
) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> ClassTabScreen(onClickClass = {
                onClickClass()
            })

            1 -> TopicTabScreen(
                onClickTopic = {
                    onClickTopic()
                }
            )

            2 -> VocabularyTabScreen(
                onClickVocal = {
                    onClickVocal()
                }
            )

            3 -> NumberScreen(
                onClickNumber = {
                    onClickNumber()
                }
            )

            4 -> AlphabetTabScreen(
                onClickAlphabet = {
                    onClickAlphabet()
                }
            )
        }
    }
}

data class LearnItem(
    val title: String,
    val icon: Int,
    val color: Color
)

val list = listOf(
    LearnItem("Classroom", R.drawable.ic_classroom, PurpleStart),
    LearnItem("Topic", R.drawable.ic_topic, BlueStart),
    LearnItem("Vocabulary", R.drawable.ic_vocabulary, OrangeStart),
    LearnItem("Number", R.drawable.ic_number, GreenStart),
    LearnItem("Alphabet", R.drawable.ic_alphabet, GreenStart),
)

enum class TabPage {
    Class, Topic, Vocal, Number, Alpha
}