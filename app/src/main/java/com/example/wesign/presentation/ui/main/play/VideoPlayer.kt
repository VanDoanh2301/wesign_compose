package com.example.wesign.presentation.ui.main.play

import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.wesign.R
import com.example.wesign.presentation.theme.Purple200
import com.example.wesign.presentation.theme.WeSignDimension
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit


@Composable
@Preview(showSystemUi = true)
fun CustomVideoPlayer(modifier: Modifier = Modifier) {
    Player()
}

@OptIn(UnstableApi::class)
@Composable
fun Player() {
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val context = LocalContext.current

    val mediaItem =
        MediaItem.fromUri(
            "https://videos.pexels.com/video-files/7172249/7172249-uhd_1440_2560_25fps.mp4"
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
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                    layoutParams =
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
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


