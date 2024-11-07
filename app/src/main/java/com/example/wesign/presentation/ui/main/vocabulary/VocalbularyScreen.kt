package com.example.wesign.presentation.ui.main.vocabulary

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.wesign.R
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.presentation.component.ShimmerListItem
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.ui.main.home.HomeScreenEvent
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseTopicScreen
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseVocabularyScreen
import com.example.wesign.utils.objectToJson
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VocabularyScreen(
    topicName: String = "Topic Name",
    topicId: Int = 0,
    vocabularyState: LazyPagingItems<Vocabulary>,
    event: (HomeScreenEvent) -> Unit = { },
    onClickVocabulary: (Vocabulary) -> Unit = { }
) {
    LaunchedEffect(Unit) {
        event(HomeScreenEvent.GetAllVocabularies(topicId))
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
                        text = topicName,
                        style = Typography.headlineSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.7f)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Filter")
                    }
                }
            )
        }
    ) {
        if (vocabularyState.itemCount == 0) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = Color.White)
                ,
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.img_empty_data),
                    contentDescription = "Empty"
                )
            }
        } else {
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
                        if (vocabularyState.loadState.refresh is LoadState.Loading) {
                            ShimmerListItem(isClass = true)
                        }
                    }
                    items(vocabularyState.itemCount) { course ->
                        CourseVocabularyScreen(vocabularyState[course]!!, onClickItem = {
                            onClickVocabulary(vocabularyState[course]!!)
                        })

                    }
                }
            }
        }
    }
}