package com.demo.quickmviappframe.ui.act

import androidx.compose.runtime.Composable
import com.demo.quickmviappframe.base.BaseAct
import com.demo.quickmviappframe.base.NoViewModel
import com.demo.quickmviappframe.entries.TitleBarSet

class ServiceAct : BaseAct<NoViewModel>() {
    override fun initConfig() {
        setTitleBarInfo(TitleBarSet("联系客服"))
    }

    override fun initListener() {

    }

    override fun initData() {

    }

    @Composable
    override fun initComposeLayout() {

    }
}