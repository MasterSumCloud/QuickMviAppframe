package com.demo.quickmviappframe.ui.pages

import android.content.Context
import android.text.TextUtils
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.StringUtils.getString
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.core.AppConfig
import com.demo.quickmviappframe.dialog.LoginYSZCDialog
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.ui.act.LoginAct
import com.demo.quickmviappframe.ui.vm.LoginActVM
import com.demo.quickmviappframe.ui.widget.VerticalSpace
import com.demo.quickmviappframe.ui.widget.textfield.BackgroundComposeWithTextField
import com.demo.quickmviappframe.ui.widget.textfield.GoodTextField
import com.demo.quickmviappframe.ui.widget.textfield.HintComposeWithTextField
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.coroutines.delay

@Composable
fun LoginPage(vm: LoginActVM) {

    val phoneNumber = remember { mutableStateOf("") }
    val codeNumber = remember { mutableStateOf("") }
    val agreementPolicy = remember { mutableStateOf(false) }
    val loginUiType = remember { mutableIntStateOf(1) }//1 短信登录  2微信登录
    val rm60s = remember { mutableIntStateOf(60) }
    val rmState = remember { mutableStateOf(false) }
    //动画 准则的
    val topPd1 = animateDpAsState(if (loginUiType.intValue == 1) 28.dp else 87.dp, label = "ruleDp")
    val topPd2 = animateDpAsState(if (loginUiType.intValue == 1) 87.dp else 28.dp, label = "ruleDp")
    val showYszcLog = remember { mutableStateOf(false) }

    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(modifier = Modifier
            .padding(top = 20.dp)
            .size(55.dp, 55.dp)
            .clickable {
                val loctx = ctx as LoginAct
                loctx.finish()
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
                        vm.state.phone = it
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
                            vm.state.code = it
                        },
                        hint = HintComposeWithTextField.createTextHintCompose("短信验证码"),
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        fontSize = 14.sp,
                        horizontalPadding = 28.dp,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        background = BackgroundComposeWithTextField.createBackgroundCompose(RoundedCornerShape(21.dp), Color.White),
                        cursorBrush = SolidColor(colorResource(R.color.blue_51A0FF)),
                        keyboardActions = KeyboardActions(onDone = {

                        }, onSearch = {

                        }),
                    )

                    CountdownTimerButton(modifier = Modifier.align(Alignment.CenterVertically), rm60s, rmState) {
                        vm.getPhoneCode()
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
                    vm.state.privateChecked = agreementPolicy.value
                }, colors = RadioButtonColors(
                    selectedColor = colorResource(id = R.color.blue_51A0FF),
                    unselectedColor = colorResource(id = R.color.gray_9D9EA2),
                    disabledUnselectedColor = colorResource(id = R.color.gray_9D9EA2),
                    disabledSelectedColor = colorResource(id = R.color.gray_9D9EA2)
                ), modifier = Modifier.size(15.dp)
            )

            Text(text = "已阅读并同意", color = colorResource(id = R.color.gray_9D9EA2), fontSize = 12.sp, modifier = Modifier.padding(start = 10.dp))
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
                juadgeLoginInfo(loginUiType.intValue, vm, ctx,showYszcLog)
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

        if (showYszcLog.value) {
            LoginYSZCDialog({
                showYszcLog.value = false
            }, {
                showYszcLog.value = false
                agreementPolicy.value = true
            })
        }
    }
}

private fun juadgeLoginInfo(type: Int, selfVM: LoginActVM, ctx: Context, showYszcLog: MutableState<Boolean>) {
    if (type == 2) {
        if (!selfVM.state.privateChecked) {
            showYszcLog.value = true
        } else {
            val wxapi = WXAPIFactory.createWXAPI(ctx, AppConfig.WECHAT_APPID)
            if (wxapi.isWXAppInstalled) {
                val req = SendAuth.Req()
                req.scope = "snsapi_userinfo"
                req.state = "wechat_sdk_demo_test"
                wxapi.sendReq(req)
            } else {
                getString(R.string.wechat_not_install).toastShort()
            }
        }
    } else {
        val phone = selfVM.state.phone
        val code = selfVM.state.code
        if (TextUtils.isEmpty(phone)) {
            "请输入手机号".toastShort()
        } else if (TextUtils.isEmpty(code)) {
            "请输入验证码".toastShort()
        } else if (!RegexUtils.isMobileSimple(phone.trim())) {
            "请输入正确的手机号".toastShort()
        } else if (code.trim().length != 6) {
            "请输入正确的验证码".toastShort()
        } else if (!selfVM.state.privateChecked) {
            showYszcLog.value = true
        } else {
            selfVM.goLogin()
        }
    }
}


@Composable
fun CountdownTimerButton(
    modifier: Modifier,
    rm60s: MutableIntState,
    stateTimer: MutableState<Boolean>,
    getCode: () -> Unit = {}
) {
    var currentTime by remember { rm60s }
    var isTimerRunning by remember { stateTimer }

    LaunchedEffect(key1 = isTimerRunning) {
        while (isTimerRunning && currentTime > 0) {
            delay(1000)
            currentTime--
        }
        if (currentTime <= 0) {
            isTimerRunning = false
            currentTime = 60
        }
    }

    TextButton(
        modifier = modifier
            .padding(end = 28.dp)
            .background(color = colorResource(if (isTimerRunning) R.color.gray_9D9EA2 else R.color.blue_51A0FF), shape = RoundedCornerShape(13.dp))
            .size(78.dp, 26.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            if (!isTimerRunning) {
                getCode()
                isTimerRunning = true
            }
        },
        enabled = !isTimerRunning // 仅在倒计时未运行且时间为初始值时启用按钮
    ) {
        Text(
            if (isTimerRunning) "剩余${currentTime}秒" else "获取验证码", color = Color.White, fontSize = 12.sp
        )
    }
}


@Preview
@Composable
private fun prev() {
    LoginPage(LoginActVM())
}