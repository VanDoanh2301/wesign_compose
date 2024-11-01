package com.example.wesign.presentation.ui.main.home.learn_page.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wesign.presentation.theme.WeSignShape

@Composable
fun TabLayoutIndicator(
    tabPositions: List<TabPosition>,
    tabPage: TabPage
) {
    val transition = updateTransition(
        tabPage, label = "Tab Indicator"
    )
    val indicatorLeft by transition.animateDp(
        transitionSpec = {
            when {
                TabPage.Class isTransitioningTo TabPage.Topic ||
                        TabPage.Class isTransitioningTo TabPage.Vocal ||
                        TabPage.Class isTransitioningTo TabPage.Number ||
                        TabPage.Class isTransitioningTo TabPage.Alpha -> {
                    spring(stiffness = Spring.StiffnessVeryLow)
                }
                TabPage.Topic isTransitioningTo TabPage.Class ||
                        TabPage.Topic isTransitioningTo TabPage.Vocal ||
                        TabPage.Topic isTransitioningTo TabPage.Number ||
                        TabPage.Topic isTransitioningTo TabPage.Alpha -> {
                    spring(stiffness = Spring.StiffnessLow)
                }
                TabPage.Vocal isTransitioningTo TabPage.Class ||
                        TabPage.Vocal isTransitioningTo TabPage.Topic ||
                        TabPage.Vocal isTransitioningTo TabPage.Number ||
                        TabPage.Vocal isTransitioningTo TabPage.Alpha -> {
                    spring(stiffness = Spring.StiffnessMedium)
                }
                TabPage.Number isTransitioningTo TabPage.Class ||
                        TabPage.Number isTransitioningTo TabPage.Topic ||
                        TabPage.Number isTransitioningTo TabPage.Vocal ||
                        TabPage.Number isTransitioningTo TabPage.Alpha -> {
                    spring(stiffness = Spring.StiffnessHigh)
                }
                TabPage.Alpha isTransitioningTo TabPage.Class ||
                        TabPage.Alpha isTransitioningTo TabPage.Topic ||
                        TabPage.Alpha isTransitioningTo TabPage.Vocal ||
                        TabPage.Alpha isTransitioningTo TabPage.Number -> {
                    spring(stiffness = Spring.StiffnessHigh)
                }
                else -> {
                    spring(stiffness = Spring.StiffnessMedium)
                }
            }
        },
        label = "Indicator Left"
    ) { page ->
        tabPositions[page.ordinal].left
    }

    val indicatorRight by transition.animateDp(
        transitionSpec = {
            when {
                TabPage.Alpha isTransitioningTo TabPage.Number ||
                        TabPage.Alpha isTransitioningTo TabPage.Vocal ||
                        TabPage.Alpha isTransitioningTo TabPage.Topic ||
                        TabPage.Alpha isTransitioningTo TabPage.Class -> {
                    spring(stiffness = Spring.StiffnessVeryLow)
                }
                TabPage.Number isTransitioningTo TabPage.Alpha ||
                        TabPage.Number isTransitioningTo TabPage.Vocal ||
                        TabPage.Number isTransitioningTo TabPage.Topic ||
                        TabPage.Number isTransitioningTo TabPage.Class -> {
                    spring(stiffness = Spring.StiffnessLow)
                }
                TabPage.Vocal isTransitioningTo TabPage.Alpha ||
                        TabPage.Vocal isTransitioningTo TabPage.Number ||
                        TabPage.Vocal isTransitioningTo TabPage.Topic ||
                        TabPage.Vocal isTransitioningTo TabPage.Class -> {
                    spring(stiffness = Spring.StiffnessVeryLow)
                }
                TabPage.Topic isTransitioningTo TabPage.Alpha ||
                        TabPage.Topic isTransitioningTo TabPage.Number ||
                        TabPage.Topic isTransitioningTo TabPage.Vocal ||
                        TabPage.Topic isTransitioningTo TabPage.Class -> {
                    spring(stiffness = Spring.StiffnessLow)
                }
                TabPage.Class isTransitioningTo TabPage.Alpha ||
                        TabPage.Class isTransitioningTo TabPage.Number ||
                        TabPage.Class isTransitioningTo TabPage.Vocal ||
                        TabPage.Class isTransitioningTo TabPage.Topic -> {
                    spring(stiffness = Spring.StiffnessLow)
                }
                else -> {
                    spring(stiffness = Spring.StiffnessMedium)
                }
            }
        },
        label = "Indicator Right"
    ) { page ->
        tabPositions[page.ordinal].right
    }

    val color by transition.animateColor(
        label = "Border Color"
    ) { page ->
        when(page) {
            TabPage.Class -> Color.White
            else -> Color.White
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(4.dp)
            .fillMaxSize()
            .background(color = color, shape = WeSignShape.medium)

    )
}



