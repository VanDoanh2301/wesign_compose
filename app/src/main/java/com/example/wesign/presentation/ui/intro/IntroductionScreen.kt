package com.example.wesign.presentation.ui.intro

import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.wesign.presentation.theme.Typography
import com.example.wesign.presentation.theme.WeSignDimension
import com.example.wesign.presentation.theme.WeSignShape
import com.example.wesign.presentation.theme.primaryLight

@Composable
fun IntroductionScreen(isIntro: Boolean, onNext: () -> Unit, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WeSignDimension.PaddingLarge)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Giới thiệu hệ thống dạy và học ngôn ngữ ký hiệu tiếng Việt (WeSign)",
            style = Typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
        HtmlText(
            html = "<p>Hệ thống (Phần mềm) dạy và học ngôn ngữ ký hiệu tiếng Việt (WeSign) là một nền tảng hỗ trợ cho giáo viên cung cấp học liệu và cho phép người học (trẻ khiếm thính, người điếc, phụ huynh...) học ngôn ngữ ký hiệu tiếng Việt một cách thuận tiện. Phần mềm có thể được dùng trực tiếp từ trình duyệt trên máy tính và ứng dụng trên điện thoại (dùng Android).</p><p><br></p><p class=\\\"ql-align-justify\\\">Wesign hiện tại là phiên bản thử nghiệm. Phần mềm được sử dụng với mục đích phi thương mại. Một số dữ liệu dạy học trong phần mềm được tham khảo từ dự án QIPEDC và các tình nguyện viên. Phần mềm được phát triển bởi nhóm nghiên cứu iBME lab, Đại học Bách Khoa Hà Nội.</p><p class=\\\"ql-align-justify\\\"><br></p><p>Link phần mềm : https://we-sign-app.vercel.app/&nbsp;</p><p><br></p><p>Hướng dẫn sử dụng (tài khoản và cách dùng chi tiết)</p><p>https://bit.ly/wesign_hdsd</p><p><br></p><p>Sau khi dùng thử phần mềm, anh/chị vui lòng để lại ý kiến đánh giá qua 1 trong 3 form sau</p><p><br></p><p>1) Dành cho người dạy ngôn ngữ ký hiệu tiếng Việt&nbsp;</p><p>https://bit.ly/wesign_khao_sat_nguoi_day</p><p><br></p><p>2) Dành cho người học ngôn ngữ ký hiệu tiếng Việt&nbsp;</p><p>https://bit.ly/wesign_khao_sat_nguoi_hoc</p><p><br></p><p>3) Dành cho phụ huynh và người quan tâm ngôn ngữ ký hiệu tiếng Việt&nbsp;</p><p>https://bit.ly/wesign_khao_sat_nguoi_quan_tam</p><p><br></p><p>Nếu cần hỗ trợ sử dụng hoặc có góp ý, có thể liên lạc qua ibme.lab@gmail.com hoặc 0912834422.</p><p>Xin cảm ơn.</p><p>Nhóm nghiên cứu iBME lab</p>"
        )
        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
        Text(
            text = "WeSign version 1.0 @iBME lab, Đại học Bách Khoa Hà Nội",
            style = Typography.titleSmall
        )
        Spacer(modifier = Modifier.height(WeSignDimension.PaddingLarge))
        Button(
            onClick = {
                if (isIntro) {
                    onNext()
                } else {
                    onBack()
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryLight,
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(horizontal = WeSignDimension.PaddingSmall)
                .fillMaxWidth()
                .height(48.dp)
            ,
            shape = WeSignShape.small
        ) {
            Text(text = "Đã hiểu", style = Typography.titleSmall)
        }


    }
}

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}