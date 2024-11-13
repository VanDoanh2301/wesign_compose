package com.example.wesign.presentation.ui.main.play.components

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

@OptIn(UnstableApi::class)
@Composable
fun Player(
    vocabularyVideo: String?,
    onFullscreenButtonClicked: (Boolean) -> Unit = {}
) {
    val context = LocalContext.current
    val mediaItem = MediaItem.fromUri(vocabularyVideo!!)
    val mediaSource: MediaSource =
        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory()).createMediaSource(mediaItem)

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaSource(mediaSource)
            prepare()
        }
    }
    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    val lifecycleOwner = LocalLifecycleOwner.current
    var isFullScreen by rememberSaveable { mutableStateOf(false) }

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
    Box {
        AndroidView(
            modifier = if (isFullScreen) Modifier.fillMaxSize() else Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                ,
            factory = {
                PlayerView(context).also { playerView ->
                    playerView.player = exoPlayer
                }.apply {
                    setShowNextButton(false)
                    setShowPreviousButton(false)
                    setShowShuffleButton(false)
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    setFullscreenButtonClickListener {
                        isFullScreen = !isFullScreen
                        if (!isFullScreen) {
                            (context as Activity).requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                        } else {
                            (context as Activity).requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

                        }
                        onFullscreenButtonClicked(isFullScreen)
                    }
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
