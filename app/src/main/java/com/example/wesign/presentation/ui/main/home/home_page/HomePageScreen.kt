package com.example.wesign.presentation.ui.main.home.home_page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Vocabulary
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
    userState: UserDetailState,
    classRoomState: LazyPagingItems<ClassRoom>,
    vocabularyState: LazyPagingItems<Vocabulary>,
    event: (HomeScreenEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        event(HomeScreenEvent.GetAllClassRooms)
        event(HomeScreenEvent.GetAllVocabularies())
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = WeSignDimension.PaddingLarge, end = WeSignDimension.PaddingLarge, top = WeSignDimension.PaddingLarge)
        ) {
            item {
                CustomTopAppBar(userState.userDetail)
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingExtraLarge))
            }
            item {
                SearchContent()
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
            }
            item {
                SlideContent()
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
            }
            item {
                CoursesGrid()
            }
            item {
                RecommendedClassroomsRow(title = "Gợi ý lớp học", classrooms = classRoomState)
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
            }
            item {
                RecommendedVocabularyRow(title = "Gợi ý từ vựng", vocabularies = vocabularyState)
            }

        }
    }
}












