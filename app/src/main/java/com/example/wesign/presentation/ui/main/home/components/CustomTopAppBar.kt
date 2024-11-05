package com.example.wesign.presentation.ui.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.wesign.R
import com.example.wesign.domain.model.UserDetail
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.primaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(userDetail: UserDetail?) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = WeSignDimension.PaddingSmall),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Hello,",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.labelSmall.copy(color = Color.Gray, fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.height(WeSignDimension.PaddingSmall))
                Text(
                    "${userDetail?.name}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.labelMedium.copy(fontFamily = FontFamily(Font(R.font.inter_bold)), fontSize = 20.sp)
                )
            }

        },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(62.dp)
                    .background(Color.White)
            ) {
                if (userDetail?.avatarLocation!= null) {
                    AsyncImage(
                        model = userDetail.avatarLocation,
                        contentDescription = "Avatar",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(2.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.onboarding_page_2),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                    )
                }

            }

        },
        actions = {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color.Red,
                        modifier = Modifier.size(8.dp)
                    ) {
                        Text("")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification Icon",
                    modifier = Modifier.padding(WeSignDimension.PaddingSmall).size(34.dp),
                    tint = primaryLight,

                )
            }

        },
    )
}