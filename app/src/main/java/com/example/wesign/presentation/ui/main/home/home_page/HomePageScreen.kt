package com.example.wesign.presentation.ui.main.home.home_page

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wesign.R
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight
import com.example.wesign.presentation.ui.main.home.components.CustomTopAppBar
import com.example.wesign.presentation.ui.main.home.home_page.components.SearchContent
import com.example.wesign.presentation.ui.main.home.home_page.components.SearchOnBoarding
import com.example.wesign.presentation.ui.main.home.home_page.components.SlideContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomePageScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.bg_home_1)),
        topBar = {
            Box(
                modifier = Modifier.padding(
                    vertical = WeSignDimension.PaddingLarge,
                    horizontal = WeSignDimension.PaddingMedium
                )
            ) {
                CustomTopAppBar()
            }

        },
        content = {
            CustomBodyContent(
                modifier = Modifier.padding(it)
            )
        }
    )

}

@Composable
fun CustomBodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(WeSignDimension.PaddingLarge)) {
        SearchContent()
        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
        SlideContent()
    }
}











