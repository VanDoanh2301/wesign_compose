package com.example.wesign.presentation.ui.main.topic

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.wesign.R
import com.example.wesign.domain.model.Topic
import com.example.wesign.presentation.component.ShimmerListItem
import com.example.wesign.presentation.nav.WeSignAppState
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.ui.main.home.HomeScreenEvent
import com.example.wesign.presentation.ui.main.home.components.CustomTopAppBar
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseTopicScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopicScreen(
    className: String = "Class Name",
    classRoomId: Int = 0,
    topicState: LazyPagingItems<Topic>,
    event: (HomeScreenEvent) -> Unit,
    appState: WeSignAppState,
    onClickTopic: (Int, String) -> Unit = { _, _ -> }
) {
    LaunchedEffect(Unit) {
        if (classRoomId == -1) {
            event(HomeScreenEvent.GetAllTopics())
        } else {
            event(HomeScreenEvent.GetAllTopics(classRoomId))
        }

    }
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        text = className,
                        style = Typography.headlineSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.5f),
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { appState.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint =  Color.Black)
                    }
                },
                actions = {
//                    IconButton(onClick = { }) {
//                        Icon(Icons.Default.Menu, contentDescription = "Filter")
//                    }
                }
            )
        }
    ) {

        if (topicState.itemCount == 0) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                ,
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.img_empty_data),
                    contentDescription = "Empty",
                    contentScale = ContentScale.Crop
                )
            }
        }
        else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(unbounded = false),
                    columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
                ) {
                    items(count = 10) {
                        if (topicState.loadState.refresh is LoadState.Loading) {
                            ShimmerListItem(isClass = true)
                        }
                    }
                    items(topicState.itemCount) { course ->
                        CourseTopicScreen(topicState[course]!!, onClickItem = {
                            onClickTopic(topicState[course]!!.id, topicState[course]!!.content)
                        })

                    }
                }
            }
        }

    }
}