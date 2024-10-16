package com.demo.quickmviappframe.ext

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils
import com.demo.quickmviappframe.base.BaseResponse
import com.demo.quickmviappframe.base.BaseViewModel
import com.demo.quickmviappframe.net.AppException
import com.demo.quickmviappframe.net.ExceptionHandle
import com.demo.quickmviappframe.util.LogU
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * net request
 * @param block 请求体方法
 * @param resultState 请求回调的ResultState数据
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    success: (T?) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求网络中..."
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) loadingChange.showDialog.postValue(loadingMessage)
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            runCatching {
                //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it) { t ->
                    LogU.dResponse(GsonUtils.toJson(it))
                    success(t)
                }
            }.onFailure { e ->
                //打印错误消息
                e.message?.loge()
                //打印错误栈信息
                e.printStackTrace()
                //失败回调
                error(ExceptionHandle.handleException(e))
            }
        }.onFailure {
            //网络请求异常 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            //打印错误消息
            it.message?.loge()
            //打印错误栈信息
            it.printStackTrace()
            //失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}

/**
 * 请求结果过滤，判断请求服务器请求结果是否成功，不成功则会抛出异常
 */
suspend fun <T> executeResponse(
    response: BaseResponse<T>, success: suspend CoroutineScope.(T?) -> Unit
) {
    coroutineScope {
//        GsonUtils.toJson(response).logHttp("网络请求")
        when {
            response.isSucces() -> {
                success(response.getResponseData())
            }

            else -> {
                throw AppException(
                    response.getResponseCode(), response.getResponseMsg(), response.getResponseMsg()
                )
            }
        }
    }
}

/**
 *  调用携程
 * @param block 操作耗时操作任务
 * @param success 成功回调
 * @param error 失败回调 可不给
 */
fun <T> BaseViewModel.launch(
    block: () -> T, success: (T) -> Unit, error: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            success(it)
        }.onFailure {
            error(it)
        }
    }
}

