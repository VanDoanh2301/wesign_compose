package com.example.wesign.presentation.ui.main.lesson

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.wesign.R
import com.example.wesign.presentation.component.ShimmerItemLessonScreen
import com.example.wesign.presentation.nav.ARG_KEY_PART
import com.example.wesign.presentation.nav.ARG_KEY_VOCABULARY
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.ui.main.lesson.components.LessonItemScreen
import com.example.wesign.presentation.ui.main.play.PlayActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    classRoomId: Int,
    state: LessonScreenState,
    event: (LessonScreenEvent) -> Unit,
    onBack: () -> Unit
) {
    val startForResult = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult -> }
    val activity = LocalContext.current as Activity
    LaunchedEffect(Unit) {
        event(LessonScreenEvent.GetLessonByClassId(classRoomId))
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
                        text = "Tất cả các bài học",
                        style = Typography.headlineSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.7f),
                        color = Color.Black
                    )

                },
                navigationIcon = {
                    IconButton(onClick = { onBack()}) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                actions = {
//                    IconButton(onClick = { }) {
//                        Icon(Icons.Default.Search, contentDescription = "Filter")
//                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            if (state.isLoading) {
                ShimmerItemLessonScreen()
            } else if (state.error.isNotEmpty()) {
                Text(text = state.error)
            } else {
                if (state.listLesson.isEmpty()) {
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
                }  else {
                    LazyVerticalGrid(
                        columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(1),
                    ) {
                        val items = state.listLesson
                        items(items.size) {
                            LessonItemScreen(position = it , lessonAPart = items[it], onLessonClick = {part ->
                                val intent = Intent(activity, PlayActivity::class.java)
                                intent.putExtra(ARG_KEY_PART,part)
                                startForResult.launch(intent)
                            })
                        }
                    }
                }

            }


        }

    }
}

