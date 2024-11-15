package com.demo.quickmviappframe.ui.act

import androidx.compose.runtime.Composable
import com.demo.quickmviappframe.base.BaseAct
import com.demo.quickmviappframe.entries.TitleBarSet
import com.demo.quickmviappframe.ui.vm.ReportViewModel

class ReportAct : BaseAct<ReportViewModel>() {
    override fun initConfig() {
        setTitleBarInfo(TitleBarSet("举报"))
    }

    override fun initListener() {

    }

    @Composable
    override fun initComposeLayout() {

    }
}