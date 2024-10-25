package com.demo.quickmviappframe.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blankj.utilcode.util.AppUtils
import com.demo.quickmviappframe.R

@Composable
fun AboutPage() {
    val version = "版本号：" + AppUtils.getAppVersionName()
    val cpright = "Copyright© 2024-2024 All Rights Reserved"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.mipmap.ic_launcher), contentDescription = "appIcon", modifier = Modifier
                .padding(top = 100.dp)
                .size(100.dp)
        )

        Text(version, modifier = Modifier.padding(top = 30.dp), color = Color.Black)
        Text(cpright, modifier = Modifier.padding(top = 10.dp), color = colorResource(R.color.gray_a5a8ac))

    }
}

@Preview
@Composable
private fun pv() {
    AboutPage()
}
