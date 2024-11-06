package com.example.wesign.presentation.ui.main.home.learn_page.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.wesign.R
import com.example.wesign.presentation.theme.WeSignDimension


@Composable
fun VocabularyTabScreen(onClickVocal: () -> Unit = {  }) {

    Box(modifier = Modifier.fillMaxSize().padding(WeSignDimension.PaddingLarge)) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(unbounded = false),
            columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
        ) {
//            items(courseTopics.size) { course ->
//                CourseTopic(course = courseTopics[course], isClassed = false) {
//                    onClickVocal(courseTopics[course])
//                }
//            }
        }
    }
}