package com.example.wesign.presentation.ui.main.home.profile_page

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wesign.R
import com.example.wesign.presentation.nav.MainRoutes
import com.example.wesign.presentation.nav.Screen
import com.example.wesign.presentation.nav.WeSignAppState
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.presentation.ui.ThemeViewModel
import com.example.wesign.presentation.ui.auth.login.LOGIN_TEXT
import com.example.wesign.presentation.ui.main.home.HomeScreenEvent
import com.example.wesign.utils.SharedPreferencesUtils

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfilePageScreen(
    appState: WeSignAppState,
    event: (HomeScreenEvent) -> Unit,
    themeViewModel: ThemeViewModel
) {
    var isDarkTheme by remember { mutableStateOf(themeViewModel.isDarkThemeEnabled.value) }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Button(
                onClick = {
                    event(HomeScreenEvent.Logout)
                    appState.navigateWithPopUpTo(
                        Screen.Auth.route,
                        inclusive = true,
                        popUpToRoute = MainRoutes.Home.route
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryLight,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(horizontal = WeSignDimension.PaddingLarge)
                    .fillMaxWidth()
                    .height(48.dp),
                shape = WeSignShape.small
            ) {
                Text(text = LOGIN_TEXT, style = Typography.titleSmall)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                   isDarkTheme = !isDarkTheme
                    themeViewModel.setTheme(isDarkTheme)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryLight,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(horizontal = WeSignDimension.PaddingLarge)
                    .fillMaxWidth()
                    .height(48.dp),
                shape = WeSignShape.small
            ) {
                Text(text = LOGIN_TEXT, style = Typography.titleSmall)
            }
        }

    }
}







