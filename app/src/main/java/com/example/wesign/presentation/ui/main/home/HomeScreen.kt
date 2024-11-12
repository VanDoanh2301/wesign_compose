package com.example.wesign.presentation.ui.main.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.wesign.presentation.nav.BottomNavGraph
import com.example.wesign.presentation.nav.WeSignAppState
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.ui.ThemeViewModel
import com.example.wesign.presentation.ui.main.home.components.CustomBottomAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    appState: WeSignAppState,
    viewModel: HomeViewModel,
    themeViewModel: ThemeViewModel,
) {
    val navController = rememberNavController()

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            ,
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(it) ) {
                BottomNavGraph(navController, appState,  viewModel, themeViewModel)
            }
        },
        bottomBar = {
            Box(modifier = Modifier.background(color = Color.White).padding(top = WeSignDimension.PaddingMedium), contentAlignment = Alignment.Center) {
                CustomBottomAppBar(navController)
            }

        }
    )
}