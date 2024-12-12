package com.demo.quickmviappframe.ui.vm

import androidx.lifecycle.viewModelScope
import com.demo.quickmviappframe.App
import com.demo.quickmviappframe.base.BaseViewModel
import com.demo.quickmviappframe.base.NoUiEffect
import com.demo.quickmviappframe.ext.request
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.net.apiService
import com.demo.quickmviappframe.util.Tos
import kotlinx.coroutines.launch

class LoginActVM : BaseViewModel<LoginCodeState, LoginIntent, NoUiEffect>() {

    init {
        viewModelScope.launch {
            intent.collect {
                handleIntent(it)
            }
        }
    }

    private fun getPhoneCode() {
        request({ apiService.getPhoneCode(uiState.value.phone) },
            {
                "短信已发送".toastShort()
            },
            { Tos.showToastShort(it.errorMsg) })
    }


    private fun handleIntent(intent: LoginIntent) {
        viewModelScope.launch {
            when (intent) {
                LoginIntent.GetPhoneCode -> getPhoneCode()
                LoginIntent.GoLogin -> goLogin()
                LoginIntent.StartAuthLogin -> startAuthLogin()
                else -> {}
            }
        }
    }

    private fun goLogin() {
        val params = mutableMapOf<String, String?>()
        params.put("phone", uiState.value.phone)
        params.put("code", uiState.value.code)
        request({ apiService.goLogin(params) }, {
            "登录成功".toastShort()
//            GeneralUtil.loginSuccess(it, "phone_auth")
//            loginState.set(App.isLogin)
//            upgd(3, it?.user_id, null)
        }, { Tos.showToastShort(it.errorMsg) })
    }


    private fun startAuthLogin() {
        App.app.goLogin()
    }

    override fun initialState(): LoginCodeState {
        return LoginCodeState()
    }
}

data class LoginCodeState(
    var phone: String = "",
    var code: String = "",
    var privateChecked: Boolean = false,
    var yszcDialogShow: Boolean = false
)

sealed class LoginIntent {
    data object GetPhoneCode : LoginIntent()
    data object GoLogin : LoginIntent()
    data object StartAuthLogin : LoginIntent()
}