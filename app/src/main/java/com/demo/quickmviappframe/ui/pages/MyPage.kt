package com.demo.quickmviappframe.ui.pages

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ActivityUtils.startActivity
import com.blankj.utilcode.util.ClipboardUtils
import com.demo.quickmviappframe.App
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.core.Constent
import com.demo.quickmviappframe.core.UCS
import com.demo.quickmviappframe.entries.MyActFunListItemBean
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.ui.act.LoginAct
import com.demo.quickmviappframe.ui.act.SettingAct
import com.demo.quickmviappframe.ui.act.WebAct
import com.demo.quickmviappframe.ui.vm.MyViewModel
import com.demo.quickmviappframe.ui.widget.CustomSwitch
import com.demo.quickmviappframe.ui.widget.HorizontalSpace

@Composable
fun MyPage(navController: NavHostController) {
    MyUI(navController)
}

@Composable
private fun MyUI(navController: NavHostController?, vm: MyViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HeaderUI(navController, vm)
        LazyColumn(
            modifier = Modifier
                .padding(start = 14.dp, end = 14.dp, top = 345.dp, bottom = 14.dp)
                .fillMaxWidth()
                .background(color = Color.White, RoundedCornerShape(12.dp))
        ) {
            vm.tablist.forEach {
                item { singleItem(it, vm, navController) }
            }
        }
    }
}

@Composable
fun singleItem(item: MyActFunListItemBean, vm: MyViewModel, navController: NavHostController?) {
    val checkedState = remember {
        mutableStateOf(item.switchChecked)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxSize()
            .clickable {
                itemClick(item, navController)
            }) {
            Image(
                painter = painterResource(id = item.icon), contentDescription = null, modifier = Modifier
                    .padding(start = 16.dp)
                    .size(19.dp)
            )
            Text(text = item.text, fontSize = 14.sp, modifier = Modifier.padding(start = 14.dp))
        }

        if (item.showArr) {
            Image(
                painter = painterResource(id = R.mipmap.arr_gray_row),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(12.dp)
                    .align(Alignment.CenterEnd),
            )
        } else if (item.showCheckBox) {
            CustomSwitch(
                60.dp, 28.dp, checked = checkedState.value,
                onCheckedChange = {
                    item.switchChecked = it
                    checkedState.value = it
                },
                Color(0xFF8566FF), Color(0xFFF8F8F8), Color.White,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .align(Alignment.CenterEnd),
            )
        }
    }
}

@Composable
private fun HeaderUI(naviCl: NavHostController?, vm: MyViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (headerImg, userName, userIdRow, vipbg, viptx2, openVip, vipTqTx) = createRefs()
        Box(modifier = Modifier
            .size(90.dp)
            .background(Color.White, RoundedCornerShape(45.dp))
            .clip(
                CircleShape
            )
            .clickable {
                if (!App.isLogin) {
                    App.app.goLogin()
                }
            }
            .constrainAs(headerImg) {
                top.linkTo(parent.top, margin = 63.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, contentAlignment = Alignment.Center
        ) {
            if (vm.state.headerImageUrl.isNullOrEmpty()) {
                Image(painter = painterResource(R.mipmap.header_ofmy_default),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(
                            CircleShape
                        )
                        .clickable {
                            if (!App.isLogin) {
                                App.app.goLogin()
                            }
                        })
            } else {
                AsyncImage(
                    model = vm.state.headerImageUrl, contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(
                            CircleShape
                        )
                        .clickable {
                            if (!App.isLogin) {
                                App.app.goLogin()
                            }
                        }
                )
            }
        }



        Text(
            text = vm.state.userName ?: "",
            fontSize = 20.sp,
            color = colorResource(id = R.color.black_010106),
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .clickable {
                    if (!App.isLogin) {
                        App.app.goLogin()
                    }
                }
                .constrainAs(userName) {
                    top.linkTo(headerImg.bottom, 13.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        Row(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(6.dp))
                .padding(horizontal = 6.dp)
                .height(20.dp)
                .clickable {
                    ClipboardUtils.copyText(vm.state.userName)
                }
                .constrainAs(userIdRow) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(userName.bottom, 10.dp)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "ID：${vm.state.userId}", color = colorResource(id = R.color.black_010106), fontSize = 12.sp)

            HorizontalSpace(dp = 6.dp)

            Image(painter = painterResource(id = R.mipmap.copy_icon), contentDescription = null, modifier = Modifier
                .size(12.dp)
                .clickable {
                    ClipboardUtils.copyText(vm.state.userId)
                    "复制成功".toastShort()
                })
        }

        Image(painter = painterResource(id = R.mipmap.bg_my_mid),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxWidth()
                .height(80.dp)
                .constrainAs(vipbg) {
                    top.linkTo(userIdRow.bottom, 24.dp)
                })

        Image(
            painter = painterResource(id = R.mipmap.vip_tequan),
            contentDescription = null,
            modifier = Modifier
                .size(192.dp, 18.dp)
                .constrainAs(vipTqTx) {
                    top.linkTo(vipbg.top)
                    start.linkTo(vipbg.start, 30.dp)
                    bottom.linkTo(viptx2.top)
                }
        )

        Text(text = vm.state.vipTime ?: "",
            fontSize = 12.sp,
            color = colorResource(id = R.color.purple_F0E0FF),
            modifier = Modifier.constrainAs(viptx2) {
                start.linkTo(vipbg.start, 30.dp)
                top.linkTo(vipTqTx.bottom)
                bottom.linkTo(vipbg.bottom)
            })

        if (vm.state.isVip != true) {
            Text(
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.purple_6E52FB), shape = RoundedCornerShape(13.dp)
                    )
                    .border(width = 1.dp, color = colorResource(id = R.color.purple_C0B6FF), RoundedCornerShape(13.dp))
                    .size(70.dp, 26.dp)
                    .padding(top = 1.dp)
                    .clickable {
                        if (!App.isVip) {
                            "去会员页".toastShort()
                        }
                    }
                    .constrainAs(openVip) {
                        end.linkTo(parent.end, 25.dp)
                        top.linkTo(vipbg.top)
                        bottom.linkTo(vipbg.bottom)
                    },
                fontWeight = FontWeight.Medium,
                text = "立即开通", color = Color.White, fontSize = 12.sp,
                textAlign = TextAlign.Center, lineHeight = 2.em
            )
        }
    }
}

private fun itemClick(item: MyActFunListItemBean?, navController: NavHostController?) {
    when (item?.text) {
        "联系客服" -> {
//            startActivityJuageLogin(ContactServiceAct::class.java)
        }

        "用户协议" -> {
            val intent = Intent(ActivityUtils.getTopActivity(), WebAct::class.java)
            intent.putExtra(Constent.WEB_URL, UCS.YHXY_URL)
            intent.putExtra(Constent.TITLE_TEXT, "用户协议")
            startActivity(intent)
        }

        "隐私协议" -> {
            val intent = Intent(ActivityUtils.getTopActivity(), WebAct::class.java)
            intent.putExtra(Constent.WEB_URL, UCS.YSZC_URL)
            intent.putExtra(Constent.TITLE_TEXT, "隐私协议")
            startActivity(intent)
        }

        "设置" -> {
            startActivity(SettingAct::class.java)
        }

        "举报" -> {
//            startActivity(ReportAct::class.java)
        }

        "测试" -> {
//            navController?.navigate(RouteConfig.LOGIN)
            startActivity(LoginAct::class.java)
//                startActivity(KeyboardGuideAct::class.java)
//                startActivity(KeyboardChatDmAct::class.java)
//                startActivity(KeyboardSettingAct::class.java)
//                ContextCompat.startForegroundService(App.app,Intent(App.app, FloatingWindowService::class.java))
//                startService(Intent(App.app, FloatingWindowService::class.java))
        }
    }
}


@Preview
@Composable
private fun prev() {
    MyUI(null)
}