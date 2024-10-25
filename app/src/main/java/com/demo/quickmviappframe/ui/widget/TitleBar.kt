package com.demo.quickmviappframe.ui.widget

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SizeUtils
import com.demo.quickmviappframe.R


@Composable
fun TitleBar(title: String = "", backClick: () -> Unit = {}, color: Color = Color.White, rightButton: (@Composable () -> Unit)? = null) {
    val ctx = LocalContext.current as ComponentActivity
    val barHeight = SizeUtils.px2dp(BarUtils.getStatusBarHeight().toFloat())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
    ) {
        VerticalSpace(barHeight.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    backClick()
                    ctx.finish()
                },
                modifier = Modifier.size(39.dp, 50.dp)
            ) {
                Icon(painter = painterResource(R.mipmap.arr_left_black), "backIcon", modifier = Modifier.size(9.dp, 17.dp))
            }


            Text(title, color = colorResource(R.color.black_2C2C34), fontSize = 18.sp, fontWeight = FontWeight.Medium)


            if (rightButton != null) {
                rightButton()
            } else {
                Box(modifier = Modifier.size(50.dp))
            }
        }
    }
}

@Preview
@Composable
private fun pv() {
    TitleBar("标题")
}