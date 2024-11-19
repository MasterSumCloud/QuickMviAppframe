package com.demo.quickmviappframe.base

import android.Manifest
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
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.PermissionUtils
import com.demo.quickmviappframe.dialog.LoadingDialog
import com.demo.quickmviappframe.dialog.TextDialog
import com.demo.quickmviappframe.entries.TitleBarSet
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.ui.widget.TitleBar
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

abstract class BaseAct<VM : BaseViewModel<*, *, *>> : ComponentActivity() {

    private lateinit var viewModel: VM
    private lateinit var mLoadingDialog: LoadingDialog
    private var titleInfo: TitleBarSet = TitleBarSet()
    private lateinit var externalDialog: TextDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBaseConfig()
        //初始化配置
        initConfig()
        initListener()
        initData()

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
        externalDialog = TextDialog(this)
    }

    abstract fun initConfig()
    abstract fun initListener()
    abstract fun initData()

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
        viewModel.viewModelScope.launch {
            viewModel.loadingIntent.collect {
                when (it) {
                    is LoadUiIntent.Loading -> {
                        if (it.isShow) {
                            showLoading()
                        } else {
                            disLoading()
                        }
                    }

                    is LoadUiIntent.ShowTxDialog -> {
                        if (it.isShow) {

                        }
                    }
                }
            }
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


    open fun reqRWPermission(showMsg: String = "您当前使用的功能需要存储权限，目的是方便您选择文件后能正常的进行编辑和处理，若拒绝该权限将无法正常使用哦。") {
        val granted = PermissionUtils.isGranted(
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (!granted) {
            externalDialog.showTextDialog(showMsg,
                object : TextDialog.onTextDialogBtnListener {
                    override fun onClickOk() {
                        PermissionUtils.permission(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ).callback(object : PermissionUtils.SimpleCallback {
                            override fun onGranted() {
                                onRWGranted()
                            }

                            override fun onDenied() {
                                "您拒绝了存储权限，无法为您提供识别服务".toastShort()
                            }
                        }).request()
                    }

                    override fun onClickCancel() {

                    }

                })
        } else {
            onRWGranted()
        }
    }

    open fun onRWGranted() {}
}