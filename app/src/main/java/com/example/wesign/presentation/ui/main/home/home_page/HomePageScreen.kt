package com.example.wesign.presentation.ui.main.home.home_page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.wesign.domain.model.UserDetail
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.ui.main.home.components.CustomTopAppBar
import com.example.wesign.presentation.ui.main.home.home_page.components.CoursesGrid
import com.example.wesign.presentation.ui.main.home.home_page.components.RecommendedCoursesRow
import com.example.wesign.presentation.ui.main.home.home_page.components.SearchContent
import com.example.wesign.presentation.ui.main.home.home_page.components.SlideContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePageScreen(userDetail: UserDetail?) {
    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CustomBodyContent(userDetail)
            }
        }
    )
}

@Composable
fun CustomBodyContent(userDetail: UserDetail?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(WeSignDimension.PaddingLarge)
    ) {
        item {
            CustomTopAppBar(userDetail)
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
            RecommendedCoursesRow(isClassed = true, title = "All Classes")
            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
        }
        item {
            RecommendedCoursesRow()
        }

    }
}











