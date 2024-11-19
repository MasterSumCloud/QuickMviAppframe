package com.demo.quickmviappframe.net

import com.demo.quickmviappframe.entries.LoginResponBean
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
    suspend fun goLogin(@FieldMap phone: MutableMap<String, String?>): ApiResponse<LoginResponBean>


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

    /**
     * 举报
     */
    @Multipart
    @POST("api/complaint")
    suspend fun report(
        @Part("content") content: String, @Part("contact_information") contact_information: String,
        @Part file: MutableList<MultipartBody.Part>
    ): ApiResponse<String>
}