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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wesign.presentation.nav.BottomHomeRoutes
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CustomBottomAppBar(
    navController: NavHostController = rememberNavController(),
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
    Box(modifier = Modifier.background(color = Color.White)) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = WeSignDimension.PaddingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            bottomBarItems.forEachIndexed { index, item ->
                var itemRect by remember { mutableStateOf(Rect.Zero) }
                CustomBottomBarItem(
                    modifier = Modifier, item,
                    currentRoute == item.route,
                    onClickItem = {
                        selectedItem = index
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })

            }
        }
    }


}


@Composable
fun Float.toDp(): Dp = (this / LocalDensity.current.density).dp

@Composable
fun CustomBottomBarItem(
    modifier: Modifier = Modifier,
    item: BottomBarItem,
    isSelect: Boolean,
    onClickItem: () -> Unit = {}
) {
    val backgroundColor by animateColorAsState(targetValue = if (isSelect) primaryLight else Color.White)
    val iconTintColor by animateColorAsState(targetValue = if (isSelect) Color.White else primaryLight)
    val scale by animateFloatAsState(targetValue = if (isSelect) 1.2f else 1.0f)

    Box(
        modifier = modifier
            .scale(scale) // Apply the scaling effect
            .clip(CircleShape)
            .background(color = backgroundColor)
            .padding(WeSignDimension.PaddingSmall)
            .clickable {
                onClickItem()
            },
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                imageVector = item.icon,
                contentDescription = item.contentDescription,
                modifier = Modifier.padding(WeSignDimension.PaddingMedium),
                tint = iconTintColor
            )
            AnimatedVisibility(!isSelect) {
                Text(
                    text = item.contentDescription,
                    color = iconTintColor,
                    style = Typography.titleSmall
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
