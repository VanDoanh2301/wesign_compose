package com.example.wesign.presentation.ui.main.play

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.example.wesign.R
import com.example.wesign.databinding.FragmentVideoBinding
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.model.VocabularyImage
import com.example.wesign.domain.model.VocabularyVideo
import com.example.wesign.domain.model.WordType
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.presentation.ui.auth.login.LOGIN_TEXT
import com.example.wesign.presentation.ui.auth.login.LoginScreenEvent
import com.example.wesign.presentation.ui.auth.register.REGEX_EMAIL
import com.example.wesign.utils.showToast
import kotlinx.coroutines.launch

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

// Sample Vocabulary Data
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
fun VideoPlayerScreen(vocabulary: Vocabulary, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pagerState = rememberPagerState() {
            vocabulary.videos!!.size
        }
        HorizontalPager(state = pagerState) { page ->
            if (vocabulary.videos!!.isNotEmpty()) {
                Player(vocabulary.videos[page])
            }

        }

        if (vocabulary.videos!!.size >= 2) {
            Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = WeSignDimension.PaddingLarge),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryLight,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = WeSignShape.small
                ) {
                    Text(text = "Tiếp theo", style = Typography.titleSmall)
                }

                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryLight,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = WeSignShape.small
                ) {
                    Text(text = "Tiếp theo", style = Typography.titleSmall)
                }
            }
        }
        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
        Text(
            text = "Image Information",
            style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily(Font(R.font.inter_bold))),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = WeSignDimension.PaddingLarge)
        )
        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))

        if (vocabulary.images!!.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(18f / 9f)
            ) {
                items(vocabulary.images.size) {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = WeSignDimension.PaddingLarge)
                            .border(2f.dp, Color.Black, WeSignShape.medium)
                    ) {
                        AsyncImage(
                            model = vocabulary.images[it].location,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                }
            }
        }

    }

}

@OptIn(UnstableApi::class)
@Composable
fun Player(vocabularyVideo: VocabularyVideo) {
    val context = LocalContext.current
    val mediaItem = MediaItem.fromUri(vocabularyVideo.location!!)
    val mediaSource: MediaSource =
        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory()).createMediaSource(mediaItem)

    // Initialize ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaSource(mediaSource)
            prepare()  // Prepares the media source
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    var isFullscreen by remember { mutableStateOf(false) }
    AndroidViewBinding(
        factory = FragmentVideoBinding::inflate,
        modifier = Modifier.fillMaxWidth().aspectRatio(16f/9)
    ) {

        videoView.apply {
            hideController()
            useController = true
            resizeMode =  AspectRatioFrameLayout.RESIZE_MODE_FILL
            player = exoPlayer
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            val playFullScreen: ImageView = findViewById(R.id.bt_fullscreen)
            playFullScreen.setOnClickListener {
                isFullscreen = !isFullscreen
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    exoPlayer.playWhenReady = true
                    exoPlayer.play()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    exoPlayer.playWhenReady = false
                    exoPlayer.pause()
                }
                Lifecycle.Event.ON_DESTROY -> {
                    exoPlayer.release()
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .aspectRatio(16f / 9f)
    )
}



