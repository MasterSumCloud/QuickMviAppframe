package com.demo.quickmviappframe.net

import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.Objects

interface APIService {

    /**
     * 登录获取短信验证
     */
    @FormUrlEncoded
    @POST("api/send/code")
    suspend fun getPhoneCode(@Field("phone") phone: String): ApiResponse<String>

    /**
     * 登录接口
     */
    @FormUrlEncoded
    @POST("api/login")
    suspend fun goLogin(@FieldMap phone: MutableMap<String, String?>): ApiResponse<String>


    /**
     * 退出登录
     */
    @GET("api/loginout")
    suspend fun unRegist(): ApiResponse<String>


    /**
     * 推广
     */
    @FormUrlEncoded
    @POST("api/advert/callback")
    suspend fun appTuiGuangRegister(@FieldMap map: java.util.HashMap<String, Any?>): ApiResponse<Objects>


}