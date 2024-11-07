package com.example.wesign.presentation.ui.main.home.learn_page.components

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.wesign.R
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.presentation.component.ShimmerListItem
import com.example.wesign.presentation.nav.ARG_KEY_VOCABULARY
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseClassScreen
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseVocabularyScreen
import com.example.wesign.presentation.ui.main.play.PlayActivity


@Composable
fun VocabularyTabScreen(
    vocabularyState: LazyPagingItems<Vocabulary>,
    onClickVocal: (Vocabulary) -> Unit = {  }) {

    val startForResult = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult -> }
    val activity = LocalContext.current as Activity
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
                if (vocabularyState.loadState.refresh is LoadState.Loading) {
                    ShimmerListItem(isClass = true)
                }
            }
            items(vocabularyState.itemCount) { course ->
                CourseVocabularyScreen (vocabularyState[course]!!, onClickItem = {
                    val intent = Intent(activity, PlayActivity::class.java)
                    intent.putExtra(ARG_KEY_VOCABULARY,vocabularyState[course]!!)
                    startForResult.launch(intent)
                })

            }
        }
    }
}