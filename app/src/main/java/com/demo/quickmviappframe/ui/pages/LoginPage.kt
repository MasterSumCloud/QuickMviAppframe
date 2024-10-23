package com.demo.quickmviappframe.ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.ui.widget.VerticalSpace
import com.demo.quickmviappframe.ui.widget.textfield.BackgroundComposeWithTextField
import com.demo.quickmviappframe.ui.widget.textfield.GoodTextField
import com.demo.quickmviappframe.ui.widget.textfield.HintComposeWithTextField

@Composable
fun LoginPage() {

    val phoneNumber = remember { mutableStateOf("") }
    val codeNumber = remember { mutableStateOf("") }
    val agreementPolicy = remember { mutableStateOf(false) }
    val loginUiType = remember { mutableIntStateOf(1) }//1 短信登录  2微信登录
    //动画 准则的
    val topPd1 = animateDpAsState(if (loginUiType.intValue == 1) 28.dp else 87.dp, label = "ruleDp")
    val topPd2 = animateDpAsState(if (loginUiType.intValue == 1) 87.dp else 28.dp, label = "ruleDp")


//    val ctx = LocalContext.current as LoginAct
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(modifier = Modifier
            .padding(top = 20.dp)
            .size(55.dp, 55.dp)
            .clickable {
//                ctx.finish()
            }
        ) {
            Image(
                painter = painterResource(R.mipmap.arr_left_black_login), "iconBack", modifier = Modifier
                    .align(Alignment.Center)
                    .size(16.dp)
            )
        }

        AnimatedVisibility(visible = loginUiType.intValue == 1) {
            Column {
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
            }
        }

        AnimatedVisibility(visible = loginUiType.intValue == 2) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.mipmap.header_ofmy_default), contentDescription = "iconAppIcon", modifier = Modifier.size(76.dp))
                Text(
                    text = stringResource(R.string.app_name),
                    color = colorResource(R.color.black_2C2C34),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }


        Row(
            modifier = Modifier.padding(top = topPd1.value, start = 31.dp, end = 31.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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

            Text(text = "已阅读并同意", color = colorResource(id = R.color.gray_9D9EA2), fontSize = 12.sp, modifier = Modifier.padding(start = 5.dp))
            Text(text = "《用户协议》", modifier = Modifier.clickable {
                "去用户协议".toastShort()
            }, color = colorResource(id = R.color.blue_51A0FF), fontSize = 12.sp)
            Text(text = "和", color = colorResource(id = R.color.gray_9D9EA2), fontSize = 12.sp)
            Text(text = "《隐私政策》", modifier = Modifier.clickable {
                "去隐私政策".toastShort()
            }, color = colorResource(id = R.color.blue_51A0FF), fontSize = 12.sp)
        }

        TextButton(
            onClick = {
                "去登录".toastShort()
            }, colors = ButtonDefaults.textButtonColors(
                containerColor = colorResource(R.color.blue_51A0FF),
                contentColor = Color.White
            ), modifier = Modifier
                .padding(top = topPd2.value)
                .fillMaxWidth()
                .padding(horizontal = 31.dp)
        ) {
            Text(if (loginUiType.intValue == 1) "登录" else "微信登录", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 31.dp)) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = colorResource(R.color.gray_f2), thickness = 1.dp)
            Text("其他登录方式", modifier = Modifier.padding(horizontal = 5.dp), color = colorResource(R.color.gray_98A0B0), fontSize = 12.sp)
            HorizontalDivider(modifier = Modifier.weight(1f), color = colorResource(R.color.gray_f2), thickness = 1.dp)
        }

        Row(modifier = Modifier.padding(bottom = 44.dp, top = 32.dp)) {
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(R.mipmap.icon_phone), contentDescription = "iconPhone", modifier = Modifier.size(44.dp))
                Text("手机号登录", modifier = Modifier.padding(top = 5.dp), color = colorResource(R.color.gray_98A0B0), fontSize = 12.sp)
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        if (loginUiType.intValue == 1) {
                            loginUiType.intValue = 2
                        } else {
                            loginUiType.intValue = 1
                        }
                    }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.mipmap.icon_wechat), contentDescription = "iconPhone", modifier = Modifier.size(44.dp))
                Text("微信一键登录", modifier = Modifier.padding(top = 5.dp), color = colorResource(R.color.gray_98A0B0), fontSize = 12.sp)
            }
        }
    }
}


@Preview
@Composable
private fun prev() {
    LoginPage()
}