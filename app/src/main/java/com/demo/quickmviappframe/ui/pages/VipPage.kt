package com.demo.quickmviappframe.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun VipPage(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("会员", modifier = Modifier.align(Alignment.Center))
    }
}