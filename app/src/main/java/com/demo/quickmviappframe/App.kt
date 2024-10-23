package com.demo.quickmviappframe

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.text.TextUtils
import com.blankj.utilcode.util.Utils
import com.demo.quickmviappframe.ui.act.LoginAct
import com.demo.quickmviappframe.base.BaseMessageEvent
import com.demo.quickmviappframe.core.AppConfig
import com.demo.quickmviappframe.core.AppManager
import com.demo.quickmviappframe.core.Constent
import com.demo.quickmviappframe.core.LoginMessageEvent
import com.demo.quickmviappframe.core.Toggles
import com.demo.quickmviappframe.dialog.LoadingDialog
import com.demo.quickmviappframe.entries.LoginResponBean
import com.demo.quickmviappframe.ext.toastShort
import com.demo.quickmviappframe.util.GeneralUtil
import com.demo.quickmviappframe.util.LogU
import com.demo.quickmviappframe.util.MMKVUtil
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper
import com.mobile.auth.gatewayauth.ResultCode
import com.mobile.auth.gatewayauth.TokenResultListener
import com.mobile.auth.gatewayauth.model.TokenRet
import com.tencent.bugly.Bugly
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.tencent.mmkv.MMKV
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import dagger.hilt.android.HiltAndroidApp
import org.greenrobot.eventbus.EventBus

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var app: App
        var isVip: Boolean = false
        var token: String? = null
        var appChannel: String = "test"
        var isLogin: Boolean = false
        var myActInfo: LoginResponBean? = null
    }

    private var onstartOnce: Int = 1
    private var mainCtx: Context? = null
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate() {
        super.onCreate()
        app = this
        //工具类初始化
        MMKV.initialize(this)
        Utils.init(this)
        isLogin = MMKVUtil.User.isLogin
        isVip = MMKVUtil.User.isVip
        token = MMKVUtil.User.token
        initSdks()
    }

    fun initSdks() {
        if (!MMKVUtil.AppConfig.isFirstInstallApp) {
            Bugly.init(this.applicationContext, AppConfig.BUGLYAPPID, Toggles.isRelease)
            //注册微信
            val wxApi = WXAPIFactory.createWXAPI(this, AppConfig.WECHAT_APPID, true)
            wxApi?.registerApp(AppConfig.WECHAT_APPID)
            //注册阿里一键登录
            aliAuthLoginInit()
            //初始化数据库
//            AppDataBase.getDatabase(this)
        }
        //友盟初始化
        initUMeng()
    }

    fun goLogin() {
        when (MMKVUtil.AppConfig.loginType) {
            1 -> {
                onstartOnce = 1
                mPhoneNumberAuthHelper = PhoneNumberAuthHelper.getInstance(applicationContext, mTokenResultListener)
                mPhoneNumberAuthHelper?.checkEnvAvailable(PhoneNumberAuthHelper.SERVICE_TYPE_LOGIN)
                mPhoneNumberAuthHelper?.getLoginToken(this, 5000)
                val currentActivity = AppManager.appManager?.currentActivity()
                if (GeneralUtil.isActExist(currentActivity)) {
                    if (loadingDialog != null) {
                        loadingDialog?.dismiss()
                        loadingDialog = null
                    }
                    loadingDialog = LoadingDialog(currentActivity!!)
                    loadingDialog?.show()
                }
            }

            2 -> {
                val intent = Intent(mainCtx, LoginAct::class.java)
                intent.putExtra(Constent.LOGIN_ACT_TYPE, 2)
                mainCtx?.startActivity(intent)
            }

            3 -> {
                val intent = Intent(mainCtx, LoginAct::class.java)
                intent.putExtra(Constent.LOGIN_ACT_TYPE, 1)
                mainCtx?.startActivity(intent)
            }
        }

    }

    private var mTokenResultListener: TokenResultListener? = null
    var mPhoneNumberAuthHelper: PhoneNumberAuthHelper? = null
    private val TAG = "mainactivity login auth"

    fun aliAuthLoginInit() {
        mTokenResultListener = object : TokenResultListener {
            override fun onTokenSuccess(s: String) {
                LogU.e(TAG, "获取token成功：$s")
                var tokenRet: TokenRet? = null
                try {
                    tokenRet = TokenRet.fromJson(s)
                    if (ResultCode.CODE_START_AUTHPAGE_SUCCESS == tokenRet.code) {
//                        Log.i("TAG", "唤起授权页成功：" + s);
                        loadingDialog?.dismiss()
                        loadingDialog = null
                    }
                    if (ResultCode.CODE_SUCCESS == tokenRet.code) {
//                        Log.i("TAG", "获取token成功：" + s);
                        postMessageEvent(LoginMessageEvent(Constent.LOGIN_EVENT, tokenRet.token, 1))
                        mPhoneNumberAuthHelper?.hideLoginLoading()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onTokenFailed(s: String) {
                LogU.e(TAG, "获取token失败：$s")
                loadingDialog?.dismiss()
                loadingDialog = null
                mPhoneNumberAuthHelper?.hideLoginLoading()
                var tokenRet: TokenRet? = null
                try {
                    tokenRet = TokenRet.fromJson(s)
                    if (ResultCode.CODE_ERROR_USER_CANCEL == tokenRet?.getCode()) {
                        //模拟的是必须登录 否则直接退出app的场景
                        //finish();
                    } else {
                        if (onstartOnce == 1) {
                            mainCtx?.startActivity(Intent(mainCtx, LoginAct::class.java))
                            onstartOnce++
                        }
                        if (TextUtils.equals("600008", tokenRet?.code)) {
                            "移动网络未开启,可以重打开后再次尝试".toastShort()
                        } else {
                            "打开登录页面".toastShort()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                mPhoneNumberAuthHelper?.quitLoginPage()
            }
        }
        mPhoneNumberAuthHelper = PhoneNumberAuthHelper.getInstance(this, mTokenResultListener)
        mPhoneNumberAuthHelper?.getReporter()?.setLoggerEnable(true)
        mPhoneNumberAuthHelper?.setAuthSDKInfo(AppConfig.AUTH_SECRET)
    }

    fun postMessageEvent(messageEvent: BaseMessageEvent?) {
        EventBus.getDefault().post(messageEvent)
    }

    fun setMainCtx(context: Context) {
        this.mainCtx = context
    }

    private fun initUMeng() {
        if (!MMKVUtil.AppConfig.isFirstInstallApp) {
            UMConfigure.setLogEnabled(Toggles.isNotShowLog)
            UMConfigure.preInit(this, AppConfig.UMENG_KEY, getChannelName())
            MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL)
            UMConfigure.setProcessEvent(true);
            UMConfigure.init(
                this, MMKVUtil.AppConfig.appChannel, AppConfig.UMENG_KEY, UMConfigure.DEVICE_TYPE_PHONE, ""
            )

            UMConfigure.getOaid(this) {
                MMKVUtil.AppConfig.oaid = it
            }
            LogU.d("MobclickAgent友盟正式初始化")
        } else {
            UMConfigure.setLogEnabled(Toggles.isNotShowLog)
            UMConfigure.preInit(this, AppConfig.UMENG_KEY, getChannelName())
            MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL)
            UMConfigure.setProcessEvent(true)
            LogU.d("MobclickAgent友盟预初始化")
        }
    }


    /**
     * 获取渠道名
     * @return 如果没有获取成功，那么返回值为空
     */
    private fun getChannelName(): String? {
        var channelName: String? = null
        try {
            val packageManager: PackageManager = applicationContext.getPackageManager()
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                val applicationInfo = packageManager.getApplicationInfo(
                    applicationContext.packageName, PackageManager.GET_META_DATA
                )
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData["UMENG_CHANNEL"].toString()
                        if (!Toggles.isShowTesting) {
                            appChannel = channelName
                        }
                    }
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        MMKVUtil.AppConfig.appChannel = channelName
        return channelName
    }

}