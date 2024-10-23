package com.demo.quickmviappframe.ui.act


import androidx.compose.runtime.Composable
import com.demo.quickmviappframe.base.BaseAct
import com.demo.quickmviappframe.ui.pages.LoginPage
import com.demo.quickmviappframe.ui.vm.LoginActVM

class LoginAct : BaseAct<LoginActVM>() {
    @Composable
    override fun initComposeLayout() {
        LoginPage()
    }
}