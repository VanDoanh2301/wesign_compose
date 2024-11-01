package com.example.wesign.presentation.ui.main.home.learn_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.wesign.presentation.nav.MainRoutes
import com.example.wesign.presentation.nav.WeSignAppState
import com.example.wesign.presentation.ui.main.home.learn_page.components.Tabs
import com.example.wesign.presentation.ui.main.home.learn_page.components.TabsContent
import com.example.wesign.presentation.ui.main.home.learn_page.components.list

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LearnPageScreen(appState: WeSignAppState) {
    val pagerState = rememberPagerState(pageCount = { list.size })
    Column(
        modifier = Modifier
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState,
            onClickClass = {
                appState.navigateWithPopUpTo(
                    MainRoutes.Topic.route
                )
            },
            onClickTopic = {

            },
            onClickVocal = {

            },
            onClickAlphabet = {

            },
            onClickNumber = {

            }

        )
    }
}






