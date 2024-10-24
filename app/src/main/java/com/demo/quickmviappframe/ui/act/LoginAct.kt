package com.demo.quickmviappframe.ui.act


import android.graphics.Color
import androidx.compose.runtime.Composable
import com.blankj.utilcode.util.BarUtils
import com.demo.quickmviappframe.base.BaseAct
import com.demo.quickmviappframe.ui.pages.LoginPage
import com.demo.quickmviappframe.ui.vm.LoginActVM

class LoginAct : BaseAct<LoginActVM>() {

    override fun initConfig() {
        BarUtils.setStatusBarColor(this, Color.WHITE)
    }

    override fun initListener() {

    }

    @Composable
    override fun initComposeLayout() {
        LoginPage(vm = selfVM)
    }
}