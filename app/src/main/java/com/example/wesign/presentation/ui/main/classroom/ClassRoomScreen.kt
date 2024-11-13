package com.example.wesign.presentation.ui.main.classroom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.compose.LazyPagingItems
import com.example.wesign.domain.model.ClassRoom
import com.example.wesign.presentation.nav.WeSignAppState
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.ui.main.home.HomeScreenEvent
import com.example.wesign.presentation.ui.main.home.learn_page.components.ClassTabScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassRoomScreen(
    classRoomState: LazyPagingItems<ClassRoom>,
    event: (HomeScreenEvent) -> Unit,
    appState: WeSignAppState,
    onClickClass: (Int, String) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        event(HomeScreenEvent.GetAllClassRooms)
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        text = "Tất cả",
                        style = Typography.headlineSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.5f), color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { appState.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint =  Color.Black)
                    }
                },
                actions = {
//                    IconButton(onClick = { }) {
//                        Icon(Icons.Default.Menu, contentDescription = "Filter")
//                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            ClassTabScreen(
                classRoomState = classRoomState,
                onClickClass = { classRoomId, name ->
                    onClickClass(classRoomId, name)
                }
            )
        }
    }
}