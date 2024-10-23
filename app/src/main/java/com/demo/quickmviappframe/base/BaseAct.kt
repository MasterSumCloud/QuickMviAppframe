package com.demo.quickmviappframe.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.demo.quickmviappframe.dialog.LoadingDialog
import java.lang.reflect.ParameterizedType

abstract class BaseAct<VM : BaseViewModel> : ComponentActivity() {

    private lateinit var viewModel: VM
    private lateinit var mLoadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBaseConfig()

        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                initComposeLayout()
            }
        }
    }


    private fun initBaseConfig() {
        createViewModel()
        registerUiChange()
        mLoadingDialog = LoadingDialog(this)
    }

    @Composable
    abstract fun initComposeLayout()

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
}