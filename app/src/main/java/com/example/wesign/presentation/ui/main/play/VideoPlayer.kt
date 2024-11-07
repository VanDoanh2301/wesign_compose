package com.example.wesign.presentation.ui.main.play

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
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
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.model.VocabularyVideo
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape


@Composable
fun VideoPlayerScreen(vocabulary: Vocabulary) {
    Column(
        modifier = Modifier
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


        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))


    }

}

@OptIn(UnstableApi::class)
@Composable
fun Player(vocabularyVideo: VocabularyVideo) {
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val context = LocalContext.current

    val mediaItem =
        MediaItem.fromUri(
            vocabularyVideo.location!!
        )

    val mediaSource: MediaSource =
        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(mediaItem)


    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaSource(mediaSource)
            prepare()
            playWhenReady = true
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            factory = {
                PlayerView(context).also { playerView ->
                    playerView.player = exoPlayer
                }.apply {
                    setShowNextButton(false)
                    setShowPreviousButton(false)
                    setShowShuffleButton(false)
                }
            },
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_RESUME -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_PAUSE -> {
                        it.onResume()
                    }

                    else -> Unit
                }
            }
        )


    }


}


