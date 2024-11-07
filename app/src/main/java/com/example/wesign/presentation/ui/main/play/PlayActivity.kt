package com.example.wesign.presentation.ui.main.play

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.presentation.nav.ARG_KEY_VOCABULARY
import com.example.wesign.presentation.theme.WeSignTheme

class PlayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val vocabulary = intent.getParcelableExtra<Vocabulary>(ARG_KEY_VOCABULARY)
        setContent {
            WeSignTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (vocabulary != null) {
                        VideoPlayerScreen(vocabulary,modifier = Modifier.padding(innerPadding))
                    }

                }
            }
        }
    }
}

