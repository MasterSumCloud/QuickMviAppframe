package com.demo.quickmviappframe.base

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.demo.quickmviappframe.dialog.LoadingDialog
import com.demo.quickmviappframe.entries.TitleBarSet
import com.demo.quickmviappframe.ui.widget.TitleBar
import java.lang.reflect.ParameterizedType

abstract class BaseAct<VM : BaseViewModel> : ComponentActivity() {

    private lateinit var viewModel: VM
    private lateinit var mLoadingDialog: LoadingDialog
    private var titleInfo: TitleBarSet = TitleBarSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBaseConfig()
        //初始化配置
        initConfig()
        initListener()

        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    if (!titleInfo.hideTitle) {
                        TitleBar(titleInfo.title, titleInfo.backClick, titleInfo.color, titleInfo.rightButton)
                    }
                    initComposeLayout()
                }
            }
        }
    }


    private fun initBaseConfig() {
        setStateBarLightMode(true)
        createViewModel()
        registerUiChange()
        mLoadingDialog = LoadingDialog(this)
    }

    abstract fun initConfig()
    abstract fun initListener()

    @Composable
    abstract fun initComposeLayout()


    fun setTitleBarInfo(titleInfo: TitleBarSet) {
        this.titleInfo = titleInfo
    }

    fun setStateBarLightMode(light: Boolean, color: Int = Color.WHITE) {
        BarUtils.setStatusBarColor(this, color)
        BarUtils.setStatusBarLightMode(this, light)
    }

    private fun registerUiChange() {
        //显示弹窗
        viewModel.loadingChange.showDialog.observe(this) {
            showLoading()
        }
        //关闭弹窗
        viewModel.loadingChange.dismissDialog.observe(this) {
            disLoading()
        }
    }

    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val cls = type.actualTypeArguments[0] as Class<VM>
            viewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)[cls]
        }
    }


    fun showLoading() {
        mLoadingDialog.show()
    }

    fun disLoading() {
        mLoadingDialog.dismiss()
    }

    val selfVM: VM get() = viewModel
}