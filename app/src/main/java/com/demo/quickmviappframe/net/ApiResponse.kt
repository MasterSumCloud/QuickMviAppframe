package com.demo.quickmviappframe.net

import com.demo.quickmviappframe.base.BaseResponse

/***
 * 网络返回基类 适配自行修改
 */
data class ApiResponse<T>(val code: Int, val message: String, val data: T?) : BaseResponse<T>() {

    override fun isSucces() = code == 2000

    override fun getResponseCode() = code

    override fun getResponseData() = data

    override fun getResponseMsg() = message

}