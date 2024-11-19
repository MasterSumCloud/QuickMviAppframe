package com.demo.quickmviappframe.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.demo.quickmviappframe.App
import com.demo.quickmviappframe.R
import com.demo.quickmviappframe.core.Toggles
import com.demo.quickmviappframe.entries.MyActFunListItemBean
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {


    var state by mutableStateOf(UsInfoState())
        private set

    val tablist = mutableListOf<MyActFunListItemBean>().apply {
        add(MyActFunListItemBean(R.mipmap.myicon_service, "联系客服", true, false))
        add(MyActFunListItemBean(R.mipmap.myicon_yhxy, "用户协议", true, false))
        add(MyActFunListItemBean(R.mipmap.myicon_yszc, "隐私协议", true, false))
//        add(MyActFunListItemBean(R.mipmap.myicon_notify, "个性化推荐", false, true))//SPUtil.getMessagePushState()
        add(MyActFunListItemBean(R.mipmap.myicon_jubao, "举报", true, false))
        add(MyActFunListItemBean(R.mipmap.myicon_setting, "设置", true, false))
        if (Toggles.isShowTesting) {
            add(MyActFunListItemBean(R.mipmap.myicon_jubao, "测试", true, false))
        }
    }


    fun setLoginInfo() {
        if (App.isLogin) {
            val info = App.myActInfo
            if (info != null) {
                var vipTime: String? = state.vipTime
                if (App.isVip) {
                    if (info.vip_limit_time?.startsWith("21") == true) {
                        vipTime = "永久会员"
                    } else {
                        vipTime = "有效期至 ${info.vip_limit_time}"
                    }
                }

                state = state.copy(
                    userId = info.user_id,
                    userName = info.nick_name,
                    vipTime = vipTime,
                    isVip = info.vip,
                    headerImageUrl = info.img
                )
            }
        } else {
            state = state.copy(
                userId = "欢迎使用",
                userName = "去登陆/注册",
                vipTime = "尚未开通会员",
                isVip = false,
                headerImageUrl = ""
            )
        }
    }
}

data class UsInfoState(
    val userName: String? = "去登陆/注册",
    val userId: String? = "欢迎使用",
    val headerImageUrl: String? = "",
    val vipTime: String? = "尚未开通会员",
    val isVip: Boolean? = false,
    val showLoading: Boolean? = false
)