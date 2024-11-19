package com.demo.quickmviappframe.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.ui.vm.ReportIntent
import com.demo.quickmviappframe.ui.vm.ReportViewModel
import com.demo.quickmviappframe.ui.widget.textfield.GoodTextField
import com.demo.quickmviappframe.ui.widget.textfield.HintComposeWithTextField


@Composable
fun ReportPage(vm: ReportViewModel) {

    val reportContent = remember { mutableStateOf("") }
    val reportContact = remember { mutableStateOf("") }
    val currentSelectedPhotos = remember { vm.uiState.value.photos.toMutableStateList() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .background(Brush.horizontalGradient(colors = listOf(Color(0xFFB9B2FF), Color(0xFF29BFFF))))
                .padding(start = 22.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "您的意见与建议", color = Color.White, fontSize = 14.sp)
            Text(text = "是我们宝贵的财富", color = Color.White, fontSize = 14.sp)
        }

        title("请选择举报内容")

        GoodTextField(
            value = reportContent.value,
            contentAlignment = Alignment.Top,
            onValueChange = {
                reportContent.value = it
                vm.setReportContent(it)
            },
            hint = HintComposeWithTextField.createTextHintCompose("您遇到了什么问题？或者有什么建议？欢迎您反馈给我们，谢谢您的宝贵意见！"),
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxWidth()
                .height(116.dp)
                .border(width = 1.dp, color = colorResource(R.color.gray_E9EAF1), shape = RoundedCornerShape(4.dp)),
            fontSize = 12.sp,
            cursorBrush = SolidColor(colorResource(R.color.blue_51A0FF)),
            keyboardActions = KeyboardActions(onDone = {

            }, onSearch = {

            }),
        )

        title("您的联系方式")

        GoodTextField(
            value = reportContact.value,
            onValueChange = {
                reportContact.value = it
                vm.setReportContact(it)
            },
            hint = HintComposeWithTextField.createTextHintCompose("留下您的微信号或者QQ号"),
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxWidth()
                .height(44.dp)
                .border(width = 1.dp, color = colorResource(R.color.gray_E9EAF1), shape = RoundedCornerShape(4.dp)),
            fontSize = 12.sp,
            cursorBrush = SolidColor(colorResource(R.color.blue_51A0FF)),
            keyboardActions = KeyboardActions(onDone = {

            }, onSearch = {

            }),
        )

        title("上传照片（最多3张）")

        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            currentSelectedPhotos.forEach { item ->
                if (item == "default") {
                    Box(modifier = Modifier.size(100.dp, 100.dp)) {
                        Image(
                            painter = painterResource(R.mipmap.default_add_image),
                            contentDescription = "def",
                            modifier = Modifier
                                .size(93.dp, 93.dp)
                                .align(Alignment.BottomCenter)
                                .clickable {
                                    vm.sendIntent(ReportIntent.RequestPermission)
//                                    currentSelectedPhotos.add("2333")
//                                    vm.setReportPhotos(currentSelectedPhotos)
                                }
                        )
                    }
                } else if (item.isNullOrEmpty()) {
                    Box(modifier = Modifier.size(100.dp, 100.dp))
                } else {
                    Box(
                        modifier = Modifier
                            .size(100.dp, 100.dp)
                    ) {
                        AsyncImage(
                            model = item,
                            contentDescription = "selectedImage",
                            modifier = Modifier
                                .size(93.dp, 93.dp)
                                .align(Alignment.BottomCenter)
                        )

                        Image(
                            painter = painterResource(R.mipmap.delete_icon),
                            contentDescription = "delete",
                            modifier = Modifier
                                .size(20.dp, 20.dp)
                                .align(Alignment.TopEnd)
                        )
                    }
                }
            }
        }

        Box(modifier = Modifier.weight(1f))

        TextButton(
            onClick = {
                vm.postReport()
            }, modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(46.dp)
                .background(color = colorResource(R.color.blue_51A0FF), shape = RoundedCornerShape(22.dp))
        ) {
            Text(
                "举报",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
private fun title(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 18.dp)
                .size(8.dp)
                .clip(CircleShape)
                .background(color = colorResource(R.color.blue_51A0FF))
        )

        Text("请选择举报内容", color = colorResource(R.color.black_010106), fontSize = 14.sp, modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview
@Composable
private fun pv() {
    ReportPage(ReportViewModel())
}