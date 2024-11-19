package com.demo.quickmviappframe.ui.act

import androidx.compose.runtime.Composable
import com.demo.quickmviappframe.base.BaseAct
import com.demo.quickmviappframe.base.NoViewModel
import com.demo.quickmviappframe.entries.TitleBarSet
import com.demo.quickmviappframe.ui.pages.AboutPage

class AboutAct : BaseAct<NoViewModel>() {
    override fun initConfig() {
        setTitleBarInfo(TitleBarSet(title = "关于我们"))
    }

    override fun initListener() {

    }

    override fun initData() {

    }

    @Composable
    override fun initComposeLayout() {
        AboutPage()
    }
}