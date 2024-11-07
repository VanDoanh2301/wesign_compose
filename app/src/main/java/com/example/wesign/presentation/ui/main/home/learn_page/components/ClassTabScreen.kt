package com.example.wesign.presentation.ui.main.home.learn_page.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.wesign.R
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.presentation.component.ShimmerListItem
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseClassScreen

@Composable
fun ClassTabScreen(
    classRoomState: LazyPagingItems<ClassRoom>,
    onClickClass: (Int, String) -> Unit = {_,_ -> }
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            ,
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(unbounded = false),
            columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
        ) {
            items(count = 10) {
                if (classRoomState.loadState.refresh is LoadState.Loading) {
                    ShimmerListItem(isClass = true)
                }
            }
            items(classRoomState.itemCount) { course ->
                CourseClassScreen(classRoomState[course]!!, onClickItem = {
                    onClickClass(classRoomState[course]!!.classRoomId, classRoomState[course]!!.content)
                })

            }
        }
    }
}