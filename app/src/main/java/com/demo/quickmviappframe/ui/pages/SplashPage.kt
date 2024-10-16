package com.demo.quickmviappframe.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.demo.quickmviappframe.ui.router.MainRouter
import com.demo.quickmviappframe.ui.router.RouteConfig
import kotlinx.coroutines.delay

@Composable
fun SplashPage(navController: NavHostController) {

    LaunchedEffect(key1 = Unit) {
        delay(5000)
        navController.navigate(RouteConfig.MAIN_APGE)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "启动页")
    }
}