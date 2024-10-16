package com.demo.quickmviappframe.ui.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.demo.quickmviappframe.ui.pages.MainPage
import com.demo.quickmviappframe.ui.pages.SplashPage

@Composable
fun MainRouter(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RouteConfig.SPLASH) {
        composable(RouteConfig.SPLASH) {
            SplashPage(navController)
        }

        composable(RouteConfig.MAIN_APGE) {
            MainPage()
        }
    }
}