package com.example.wesign.presentation.ui.main.home.learn_page.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.theme.BlueStart
import com.example.wesign.presentation.theme.GreenStart
import com.example.wesign.presentation.theme.OrangeStart
import com.example.wesign.presentation.theme.PurpleStart
import kotlinx.coroutines.launch

val AcceptColor = Color(0xFF3DDC84)
val InProgressColor = Color(0xFFE95526)
val CompleteColor = Color(0xFFDB8608)

val list = listOf(
    LearnItem("Classroom", R.drawable.ic_classroom, PurpleStart),
    LearnItem("Topic", R.drawable.ic_topic, BlueStart),
    LearnItem("Vocabulary", R.drawable.ic_vocabulary, OrangeStart ),
    LearnItem("Number", R.drawable.ic_number, GreenStart),
    LearnItem("Alphabet", R.drawable.ic_alphabet, GreenStart),
)

enum class TabPage {
    Accept, InProgress, Complete
}

@Composable
@Preview(showBackground = true)
fun TabLayoutAnimation() {
    val pagerState = rememberPagerState(pageCount = { list.size })
    Column(
        modifier = Modifier
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}

@Composable
fun Tabs(pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    val backgroundColor by animateColorAsState(
        when(pagerState.currentPage) {
            0 -> AcceptColor
            1 -> InProgressColor
            2 -> CompleteColor
            else -> CompleteColor
        }
    )
    ScrollableTabRow (
        edgePadding = 0.dp,
        selectedTabIndex = pagerState.currentPage,
        containerColor = backgroundColor,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabLayoutIndicator(
                tabPositions = tabPositions,
                tabPage =
                when(pagerState.currentPage) {
                    0 -> TabPage.Accept
                    1 -> TabPage.InProgress
                    2  -> TabPage.Complete
                    else -> TabPage.Complete
                }
            )
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
                    Row {
                        Icon(painter = painterResource(id = list[index].icon), tint = if (pagerState.currentPage == index) Color.White else Color.White.copy(alpha = 0.5f), contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = list[index].title,
                            color = if (pagerState.currentPage == index) Color.White else Color.White.copy(alpha = 0.5f),
                            fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }

                }
            )
        }
    }
}

@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when(page) {
            0 -> TabScreen(
                data = "Accept TabLayout",
                image = R.drawable.onboarding_page_1,
                color = AcceptColor
            )
            1 -> TabScreen(
                data = "InProgress TabLayout",
                image = R.drawable.onboarding_page_2,
                color = InProgressColor
            )
            2 -> TabScreen(
                data = "Complete TabLayout",
                image = R.drawable.onboarding_page_3,
                color = CompleteColor
            )
        }
    }
}