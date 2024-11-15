package com.demo.quickmviappframe.ui.act

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.demo.quickmviappframe.base.BaseAct
import com.demo.quickmviappframe.base.BaseViewModel
import com.demo.quickmviappframe.core.Constent
import com.demo.quickmviappframe.entries.TitleBarSet

class WebAct : BaseAct<BaseViewModel>() {

    private var url: String = ""

    override fun initConfig() {
        val title = intent.getStringExtra(Constent.TITLE_TEXT) ?: ""
        setTitleBarInfo(TitleBarSet(title = title))
        url = intent.getStringExtra(Constent.WEB_URL) ?: ""
    }

    override fun initListener() {

    }

    @Composable
    override fun initComposeLayout() {
        MyWebView(url)
    }


    @Composable
    fun MyWebView(url: String) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    // 启用 JavaScript
                    settings.javaScriptEnabled = true

                    // 加载 URL
                    loadUrl(url)

                    // 其他 WebView 配置...
                }
            },
            update = { webView ->
                // 更新 WebView，例如重新加载 URL
                webView.loadUrl(url)
            }
        )
    }
}