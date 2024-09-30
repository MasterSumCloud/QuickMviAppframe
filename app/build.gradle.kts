plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.demo.quickmviappframe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.demo.quickmviappframe"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.livedata.core.ktx)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata)

    //rxjava
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
    //rxandroid
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    //cookie
    implementation("com.github.franmontiel:PersistentCookieJar:v1.0.1")
//    implementation("me.jessyan:retrofit-url-manager:1.4.0")
    //gson
    implementation("com.google.code.gson:gson:2.9.0")
    //util
    implementation("com.blankj:utilcodex:1.31.1")
    //compose constraintlayout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    //阿里云播放器
    implementation("com.aliyun.sdk.android:AliyunPlayer:6.5.0-full")
    //aili and wechat
    implementation("com.alipay.sdk:alipaysdk-android:+@aar")
    implementation("com.tencent.mm.opensdk:wechat-sdk-android:+")
    //阿里一键登录
    implementation(files("libs/auth_number_product-2.13.2.1-log-online-standard-cuum-release.aar"))
    implementation(files("libs/main-2.2.1-release"))
    implementation(files("libs/logger-2.2.1-release"))
    // 友盟统计SDK
    implementation("com.umeng.umsdk:common:9.4.7")// 必选
    implementation("com.umeng.umsdk:asms:1.4.1")// 必选
    implementation("com.umeng.umsdk:apm:1.7.0")// 错误分析模块改为独立库，看crash和性能数
    //eventbus
    implementation("org.greenrobot:eventbus:3.3.1")
    //mmkv
    implementation("com.tencent:mmkv:1.3.9")
    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    //bugly
    implementation("com.tencent.bugly:crashreport_upgrade:latest.release")
    implementation("com.tencent.bugly:nativecrashreport:latest.release")
}