package com.example.wesign.presentation.ui.main.test.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.wesign.R
import com.example.wesign.presentation.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun CustomTestTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    openDrawerListQuest: () -> Unit = {},
    title: String = "",
    titleRightTopBar:String = "",
    onFinishTest: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Text(text = title, fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            IconButton(onClick = {onBackClick() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }
        },
        actions = {
            Text(text = titleRightTopBar, style = Typography.titleMedium.copy(fontFamily = FontFamily(
                Font(R.font.inter_bold)
            )), modifier = Modifier.clickable{
                onFinishTest()
            })
            IconButton(onClick = {
                openDrawerListQuest()
            }, modifier = modifier) {
                Icon(
                    painter = painterResource(R.drawable.ic_list_question),
                    contentDescription = "Favorite", tint = Color.Black
                )
            }
        }
    )
}