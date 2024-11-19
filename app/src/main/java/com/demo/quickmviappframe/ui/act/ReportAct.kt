package com.demo.quickmviappframe.ui.act

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.demo.quickmviappframe.base.BaseAct
import com.demo.quickmviappframe.entries.TitleBarSet
import com.demo.quickmviappframe.ext.logd
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.ui.pages.ReportPage
import com.demo.quickmviappframe.ui.vm.ReportIntent
import com.demo.quickmviappframe.ui.vm.ReportViewModel
import kotlinx.coroutines.launch

class ReportAct : BaseAct<ReportViewModel>() {
    override fun initConfig() {
        setTitleBarInfo(TitleBarSet("举报"))
    }

    override fun initListener() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                selfVM.intent.collect {
                    when (it) {
                        ReportIntent.RequestPermission -> {
                            reqRWPermission()
                            "打印ReportIntent2".logd()
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    override fun initData() {

    }

    override fun onRWGranted() {
        super.onRWGranted()
        "去拿图片".toastShort()
    }

    @Composable
    override fun initComposeLayout() {
        ReportPage(vm = selfVM)
    }
}