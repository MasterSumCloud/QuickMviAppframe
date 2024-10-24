package com.demo.quickmviappframe.ui.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.demo.quickmviappframe.App
import com.demo.quickmviappframe.base.BaseViewModel
import com.demo.quickmviappframe.ext.request
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.net.apiService
import com.demo.quickmviappframe.util.Tos

class LoginActVM : BaseViewModel() {
    var state by mutableStateOf(LoginCode())
        private set

    fun getPhoneCode() {
        request({ apiService.getPhoneCode(state.phone) },
            {
                "短信已发送".toastShort()
            },
            { Tos.showToastShort(it.errorMsg) })
    }


    fun goLogin() {
        val params = mutableMapOf<String, String?>()
        params.put("phone", state.phone)
        params.put("code", state.code)
        request({ apiService.goLogin(params) }, {
//            GeneralUtil.loginSuccess(it, "phone_auth")
//            loginState.set(App.isLogin)
//            upgd(3, it?.user_id, null)
        }, { Tos.showToastShort(it.errorMsg) })
    }


    fun startAuthLogin() {
        loadingChange.showDialog.value = "show"
        App.app.goLogin()
    }
}

data class LoginCode(
    var phone: String = "",
    var code: String = "",
    var privateChecked: Boolean = false,
    var yszcDialogShow: Boolean = false
)