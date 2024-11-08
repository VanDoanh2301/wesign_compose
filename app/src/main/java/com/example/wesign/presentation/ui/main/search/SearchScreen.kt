package com.example.wesign.presentation.ui.main.search

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.wesign.R
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.domain.model.Topic
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.presentation.component.ShimmerListItem
import com.example.wesign.presentation.nav.ARG_KEY_VOCABULARY
import com.example.wesign.presentation.nav.MainRoutes
import com.example.wesign.presentation.nav.WeSignAppState
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseClassScreen
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseTopicScreen
import com.example.wesign.presentation.ui.main.home.home_page.components.CourseVocabularyScreen
import com.example.wesign.presentation.ui.main.home.home_page.components.TypeSearch
import com.example.wesign.presentation.ui.main.play.PlayActivity
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    typeSearch: String?,
    name: String?,
    vocabularyState: LazyPagingItems<Vocabulary>,
    classRoomState: LazyPagingItems<ClassRoom>,
    topicState: LazyPagingItems<Topic>,
    event: (SearchScreenEvent) -> Unit,
    appState: WeSignAppState,
) {
    LaunchedEffect(Unit) {
        event(SearchScreenEvent.OnSearchQueryChange(name!! , typeSearch!!))
    }
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().height(56.dp).padding(WeSignDimension.PaddingSmall).background(Color.White),
            ) {
                IconButton(onClick = {
                    appState.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Search",
                        modifier = Modifier.padding(8.dp)
                    )
                }
                SearchOnState(
                    name!!,
                    onValueChange = {
                    event(SearchScreenEvent.OnSearchQueryChange(it, typeSearch!!))
                })
            }

        }
    ) {
        Box(modifier = Modifier.fillMaxSize().paint(painter = painterResource(R.drawable.bg_home_1), contentScale = ContentScale.Crop).padding(it)) {
            when (typeSearch) {
                TypeSearch.CLASSROOM.title -> {
                    CustomClassResultRoomScreen(classRoomState) { classRoomId, className ->
                        appState.navigateWithPopUpTo(
                            MainRoutes.Topic.sendClassRoomIdAndName(
                                classRoomId,
                                className
                            )
                        )

                    }
                }
                TypeSearch.TOPIC.title -> {
                    CustomTopicResultScreen(topicState) { topicId, topicName ->
                        appState.navigateWithPopUpTo(
                            MainRoutes.Vocabulary.sendTopicIdAndName(
                                topicId,
                                topicName
                            )
                        )
                    }
                }
                TypeSearch.VOCABULARY.title -> {
                    CustomVocabularyResultScreen(vocabularyState)
                }
            }
        }
    }
}

@Composable
fun CustomVocabularyResultScreen(vocabularyState: LazyPagingItems<Vocabulary>) {
    val startForResult = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult -> }
    val activity = LocalContext.current as Activity

    if (vocabularyState.itemCount == 0) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
            ,
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.img_empty_data),
                contentDescription = "Empty"
            )
        }
    }
    else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                ,
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
                        val intent = Intent(activity, PlayActivity::class.java)
                        intent.putExtra(ARG_KEY_VOCABULARY,vocabularyState[course]!!)
                        startForResult.launch(intent)
                    })

                }
            }
        }
    }
}

@Composable
fun CustomTopicResultScreen(topicState: LazyPagingItems<Topic>, onClickTopic: (Int, String) -> Unit = { _, _ -> }) {
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
                contentDescription = "Empty"
            )
        }
    }
    else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                ,
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

@Composable
fun CustomClassResultRoomScreen(classRoomState: LazyPagingItems<ClassRoom>, onClickClass: (Int, String) -> Unit = { _, _ -> }) {
    if (classRoomState.itemCount == 0) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
            ,
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
            ,
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

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchOnState(
    query: String,
    onValueChange: (String) -> Unit
) {
    var name by remember { mutableStateOf(query) }
    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val recognizedText = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
            recognizedText?.let {
                name = it
                onValueChange(it)
            }
        }
    }
    BasicTextField(
        value = name,
        onValueChange = {
            name = it
            onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .border(0.5.dp, Color.Black, shape = WeSignShape.medium)
            .background(Color.White, shape =  WeSignShape.medium)
            .padding(4.dp),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                    if (name.isEmpty()) {
                        Text(text = "Tìm kiếm...", fontSize = 16.sp, color = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                        putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                        putExtra(RecognizerIntent.EXTRA_PROMPT, "Nói vào mic")
                    }
                    speechRecognizerLauncher.launch(intent)
                }) {  Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = "Search",
                    modifier = Modifier.padding(8.dp)
                )}

            }
        }
    )
}