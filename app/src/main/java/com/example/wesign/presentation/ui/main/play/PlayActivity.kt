package com.example.wesign.presentation.ui.main.play

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wesign.R
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.presentation.nav.ARG_KEY_VOCABULARY
import com.example.wesign.presentation.theme.WeSignTheme
import com.example.wesign.presentation.ui.main.home.HomeScreenEvent
import com.example.wesign.presentation.ui.main.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vocabulary = intent.getParcelableExtra<Vocabulary>(ARG_KEY_VOCABULARY)

        setContent {
            WeSignTheme {
                if (vocabulary != null) {
                    VideoPlayerScreen(
                        vocabulary,
                        modifier = Modifier
                            .fillMaxSize()
                            .paint(
                                painterResource(id = R.drawable.bg_home_1),
                                contentScale = ContentScale.Crop
                            )
                    )
                }

            }
        }
    }
}

