package com.demo.quickmviappframe.ui.act

import androidx.compose.runtime.Composable
import com.demo.quickmviappframe.base.BaseAct
import com.demo.quickmviappframe.base.BaseViewModel
import com.demo.quickmviappframe.entries.TitleBarSet

class ServiceAct : BaseAct<BaseViewModel>() {
    override fun initConfig() {
        setTitleBarInfo(TitleBarSet("联系客服"))
    }

    override fun initListener() {

    }

    @Composable
    override fun initComposeLayout() {

    }
}