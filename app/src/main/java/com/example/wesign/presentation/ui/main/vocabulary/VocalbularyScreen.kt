package com.example.wesign.presentation.ui.main.vocabulary

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.wesign.R
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseTopic

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showBackground = true)
fun VocabularyScreen(onClickTopic: (CourseTopic) -> Unit = { }) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(text = "Vocabulary", style = Typography.headlineSmall)
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
        val courseTopics = listOf(
            CourseTopic(
                "Biology for class XIII",
                "Smith J.",
                17,
                "40 Mins",
                R.drawable.teacher,
                Color(0xFF2196F3)
            ),
            CourseTopic(
                "Math for class XIII",
                "Smith J.",
                17,
                "40 Mins",
                R.drawable.teacher,
                Color(0xFFFFA726)
            ),
            CourseTopic(
                "Biology for class XIII",
                "Smith J.",
                17,
                "40 Mins",
                R.drawable.teacher,
                Color(0xFF2196F3)
            ),
            CourseTopic(
                "Math for class XIII",
                "Smith J.",
                17,
                "40 Mins",
                R.drawable.teacher,
                Color(0xFFFFA726)
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = WeSignDimension.PaddingLarge),
                columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
            ) {
                items(courseTopics.size) { course ->
                    CourseTopic(course = courseTopics[course], isClassed = false) {
                        onClickTopic(courseTopics[course])
                    }
                }
            }
        }
    }
}