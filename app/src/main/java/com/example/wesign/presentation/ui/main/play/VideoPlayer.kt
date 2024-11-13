package com.example.wesign.presentation.ui.main.play

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wesign.R
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.model.VocabularyImage
import com.example.wesign.domain.model.VocabularyVideo
import com.example.wesign.domain.model.WordType
import com.example.wesign.presentation.ui.main.play.components.CustomBodyPlayer
import com.example.wesign.presentation.ui.main.play.components.Player

@Preview(showBackground = true)
@Composable
fun VideoPlayerPreview() {
    val vocabularyImages = listOf(
        VocabularyImage(
            location = "https://inkythuatso.com/uploads/thumbnails/800/2022/05/hinh-nen-yasuo-cho-dien-thoai-dep-nhat-3-26-21-28-49.jpg",
            content = "Image 2 description",
            primary = false
        ),
        VocabularyImage(
            location = "https://static.gosugamers.net/c7/00/90/ec9fa1478899a0ec3fca6edc9062fd8bb7710c259f59000e20d9a21d9b.webp?w=1600",
            content = "Image 1 description",
            primary = true
        ),
        VocabularyImage(
            location = "https://inkythuatso.com/uploads/thumbnails/800/2022/05/hinh-nen-yasuo-cho-dien-thoai-dep-nhat-3-26-21-28-49.jpg",
            content = "Image 2 description",
            primary = false
        )
    )

    val vocabularyVideos = listOf(
        VocabularyVideo(
            location = "https://wesign.ibme.edu.vn/upload/hust-app//TVL1KB20_02.webm",
            content = "Video 1 description",
            primary = true
        ),
        VocabularyVideo(
            location = "https://wesign.ibme.edu.vn/upload/hust-app//TVL1KB20_02.webm",
            content = "Video 2 description",
            primary = false
        )
    )

    val vocabulary = Vocabulary(
        id = 1,
        content = "Hello",
        images = vocabularyImages,
        videos = vocabularyVideos,
        topicId = 101,
        topicContent = "Greetings",
        note = "Used for general greetings",
        type = WordType.WORD
    )
    VideoPlayerScreen(
        vocabulary = vocabulary
    )
}

@Composable
fun VideoPlayerScreen(
    vocabulary: Vocabulary,
    modifier: Modifier = Modifier
) {

    var isFullScreen by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState() {
        vocabulary.videos!!.size
    }


    Column(
        modifier = modifier
            .background(color = Color.Transparent)
            .paint(painterResource(R.drawable.bg_home_1), contentScale = ContentScale.Crop),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(state = pagerState, modifier = Modifier) { page ->
            if (vocabulary.videos!!.isNotEmpty()) {
                Player(vocabulary.videos[page].location, onFullscreenButtonClicked = {
                    isFullScreen = it
                })
            }

        }

        AnimatedVisibility(visible = !isFullScreen) {
            CustomBodyPlayer(pagerState, scope, vocabulary)
        }


    }

}






