package com.demo.quickmviappframe.ui.act

import androidx.compose.runtime.Composable
import com.demo.quickmviappframe.base.BaseAct
import com.demo.quickmviappframe.entries.TitleBarSet
import com.demo.quickmviappframe.ui.pages.SettingPage
import com.demo.quickmviappframe.ui.vm.SettingViewModel

class SettingAct : BaseAct<SettingViewModel>() {
    override fun initConfig() {
        setTitleBarInfo(TitleBarSet(title = "设置"))
    }

    override fun initListener() {

    }

    @Composable
    override fun initComposeLayout() {
        SettingPage(selfVM)
    }
}