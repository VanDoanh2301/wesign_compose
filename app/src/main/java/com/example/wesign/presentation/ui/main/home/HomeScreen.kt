package com.example.wesign.presentation.ui.main.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.wesign.R
import com.example.wesign.presentation.nav.BottomNavGraph
import com.example.wesign.presentation.ui.main.home.components.CustomBottomAppBar
import com.example.wesign.presentation.ui.main.home.components.CustomTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.bg_home_1)),
        topBar = {
            CustomTopAppBar()
        },
        content = {
            Box(modifier = Modifier.fillMaxSize().padding(it) ) {

                BottomNavGraph(navController)
            }
        },
        bottomBar = {
            CustomBottomAppBar(navController)
        }
    )
}