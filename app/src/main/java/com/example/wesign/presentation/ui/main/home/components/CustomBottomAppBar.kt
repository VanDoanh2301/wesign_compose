package com.example.wesign.presentation.ui.main.home.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PermContactCalendar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wesign.R
import com.example.wesign.presentation.nav.BottomHomeRoutes
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CustomBottomAppBar(
    navController: NavHostController = rememberNavController()
) {
    val bottomBarItems = listOf(
        BottomBarItem(
            Icons.Filled.Home,
            contentDescription = "Home",
            BottomHomeRoutes.BottomHome.route
        ),
        BottomBarItem(
            Icons.Filled.MenuBook,
            contentDescription = "Learn",
            BottomHomeRoutes.Learn.route
        ),
        BottomBarItem(
            Icons.Filled.PermContactCalendar,
            contentDescription = "Profile",
            BottomHomeRoutes.Profile.route
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedItem by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier.height(56.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        bottomBarItems.forEachIndexed { index, item ->
            CustomBottomBarItem(
                modifier = Modifier, item,
                currentRoute == item.route,
                onClickItem = {
                    selectedItem = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id){
                            inclusive = true
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })

        }
    }


}


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CustomBottomBarItem(
    modifier: Modifier = Modifier,
    item: BottomBarItem,
    isSelect: Boolean,
    onClickItem: () -> Unit = {}
) {
    val iconTintColor by animateColorAsState(targetValue = if (isSelect) Color.White else primaryLight)
    val scale by animateFloatAsState(targetValue = if (isSelect) 1.3f else 1.0f)
    val backgroundColor by animateColorAsState(targetValue = if (isSelect) primaryLight else Color.White)

    BoxWithConstraints(
        modifier = Modifier
            .size(56.dp)
            .background(color = backgroundColor, shape = CircleShape)
            .clickable(
                indication = null,
                interactionSource = null
            ) {
                onClickItem()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.contentDescription,
                tint = iconTintColor,
                modifier = Modifier
                    .size(28.dp)
                    .scale(scale)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
            AnimatedVisibility(visible = !isSelect) {
                Text(
                    text = item.contentDescription,
                    style = Typography.bodySmall,
                    color = iconTintColor,
                )
            }


        }

    }
}


data class BottomBarItem(
    val icon: ImageVector,
    val contentDescription: String,
    var route: String
)
