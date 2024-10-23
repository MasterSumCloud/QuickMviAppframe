package com.demo.quickmviappframe.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.ui.widget.VerticalSpace
import com.demo.quickmviappframe.ui.widget.textfield.BackgroundComposeWithTextField
import com.demo.quickmviappframe.ui.widget.textfield.GoodTextField
import com.demo.quickmviappframe.ui.widget.textfield.HintComposeWithTextField

@Composable
fun LoginPage(navController: NavHostController?) {

    val phoneNumber = remember { mutableStateOf("") }
    val codeNumber = remember { mutableStateOf("") }
    val agreementPolicy = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(modifier = Modifier
            .padding(top = 20.dp)
            .size(55.dp, 55.dp)
            .clickable {

            }
        ) {
            Image(
                painter = painterResource(R.mipmap.arr_left_black_login), "iconBack", modifier = Modifier
                    .align(Alignment.Center)
                    .size(16.dp)
            )
        }

        Text(
            "欢迎登录",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(R.color.black_2C2C34),
            modifier = Modifier.padding(start = 28.dp, top = 35.dp)
        )

        Text(
            "新手机号将直接注册并登录",
            fontSize = 12.sp,
            color = colorResource(R.color.gray_98A0B0),
            modifier = Modifier.padding(start = 28.dp, top = 10.dp)
        )
        VerticalSpace(52.dp)
        GoodTextField(
            value = phoneNumber.value,
            onValueChange = {
                phoneNumber.value = it
            },
            hint = HintComposeWithTextField.createTextHintCompose("请输入手机号"),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            fontSize = 14.sp,
            horizontalPadding = 28.dp,
            background = BackgroundComposeWithTextField.createBackgroundCompose(RoundedCornerShape(21.dp), Color.White),
            cursorBrush = SolidColor(colorResource(R.color.blue_51A0FF)),
            keyboardActions = KeyboardActions(onDone = {

            }, onSearch = {

            }),
        )

        HorizontalDivider(color = colorResource(R.color.gray_F8), thickness = 1.dp, modifier = Modifier.padding(horizontal = 28.dp))

        VerticalSpace(32.dp)
        Row {
            GoodTextField(
                value = codeNumber.value,
                onValueChange = {
                    codeNumber.value = it
                },
                hint = HintComposeWithTextField.createTextHintCompose("短信验证码"),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                fontSize = 14.sp,
                horizontalPadding = 28.dp,
                background = BackgroundComposeWithTextField.createBackgroundCompose(RoundedCornerShape(21.dp), Color.White),
                cursorBrush = SolidColor(colorResource(R.color.blue_51A0FF)),
                keyboardActions = KeyboardActions(onDone = {

                }, onSearch = {

                }),
            )

            Box(
                modifier = Modifier
                    .padding(end = 28.dp)
                    .background(color = colorResource(R.color.blue_51A0FF), shape = RoundedCornerShape(13.dp))
                    .size(78.dp, 26.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text("获取验证码", modifier = Modifier.align(Alignment.Center), color = Color.White, fontSize = 12.sp)
            }
        }

        HorizontalDivider(color = colorResource(R.color.gray_F8), thickness = 1.dp, modifier = Modifier.padding(horizontal = 28.dp))


        Row(modifier = Modifier.padding(top = 28.dp, start = 28.dp, end = 28.dp)) {
            RadioButton(
                selected = agreementPolicy.value, onClick = {
                    agreementPolicy.value = !agreementPolicy.value
                }, colors = RadioButtonColors(
                    selectedColor = colorResource(id = R.color.blue_51A0FF),
                    unselectedColor = colorResource(id = R.color.gray_9D9EA2),
                    disabledUnselectedColor = colorResource(id = R.color.gray_9D9EA2),
                    disabledSelectedColor = colorResource(id = R.color.gray_9D9EA2)
                ), modifier = Modifier.size(15.dp)
            )

            SpandText(
                Modifier
                    .padding(start = 6.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun SpandText(modifier: Modifier) {
    val annotatedString = buildAnnotatedString {
        withStyle(SpanStyle(color = colorResource(id = R.color.gray_9D9EA2), fontSize = 12.sp)) {
            append("已阅读并同意")
        }
        pushStringAnnotation("yhxy", "yhxy_annotation")
        withStyle(SpanStyle(color = colorResource(id = R.color.blue_51A0FF), fontSize = 12.sp)) {
            append("《用户协议》")
        }
        pop()
        withStyle(SpanStyle(color = colorResource(id = R.color.gray_9D9EA2), fontSize = 12.sp)) {
            append("和")
        }
        pushStringAnnotation("yszc", "yszc_annotation")
        withStyle(SpanStyle(color = colorResource(id = R.color.blue_51A0FF), fontSize = 12.sp)) {
            append("《隐私政策》")
        }
        pop()
    }

    Text(
        text = annotatedString,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { offset ->
                annotatedString.getStringAnnotations(0, 2).firstOrNull()?.let { annotation ->
                    when (annotation.tag) {
                        "yhxy" -> {
                            "点击用户协议".toastShort()
                        }

                        "yszc" -> {
                            "点击隐私政策".toastShort()
                        }

                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun prev() {
    LoginPage(null)
}